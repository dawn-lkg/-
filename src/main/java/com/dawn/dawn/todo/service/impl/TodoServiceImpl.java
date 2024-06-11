package com.dawn.dawn.todo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.rabbitmq.constant.RabbitMqConstant;
import com.dawn.dawn.todo.constant.TodoConstant;
import com.dawn.dawn.todo.entity.Todo;
import com.dawn.dawn.todo.mapper.TodoMapper;
import com.dawn.dawn.todo.service.TodoService;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dawn.dawn.todo.vo.TodoVO;
import com.dawn.dawn.todo.param.TodoParam;
import com.dawn.dawn.todo.dto.TodoDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;


/**
 * (Todo)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:43
 */
@Service("todoService")
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo>
        implements TodoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CommonPage<TodoVO> pageRel(TodoParam param) {
        IPage<Todo> pageParam = new Page<>(param.getPageNum(), param.getPageSize());
        Page<TodoVO> page = baseMapper.selectPageRel(pageParam, param);
        return CommonPage.restPage(page);
    }

    @Override
    public List<TodoVO> listRel(TodoParam param) {
        return baseMapper.selectListRel(param);
    }

    @Override
    public TodoVO getByIdRel(String id) {
        TodoParam param = new TodoParam();
        param.setId(id);
        TodoVO byRel = baseMapper.selectListRel(param).stream().findFirst().orElse(null);
        return byRel;
    }

    @Override
    public void saveTodo(TodoDto dto) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(dto, todo);
        if (!save(todo)) {
            throw new BusinessException("新增失败");
        }

        if (TodoConstant.NOT_NOTIFY == dto.getIsNotify()) {
            return;
        }
        //计算时差
        long delay = DateUtil.betweenMs(dto.getNotifyTime(), new Date());
        //发送邮件
        rabbitTemplate.convertAndSend(RabbitMqConstant.MAIL_MESSAGE_EXCHANGE, RabbitMqConstant.MAIL_MESSAGE_ROUTING_KEY,
                todo, message -> {
                    //设置消息持久化
                    MessageProperties props = message.getMessageProperties();
                    props.setHeader("x-delay", delay);
                    props.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                });
    }

    @Override
    public void updateTodo(TodoDto dto) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(dto, todo);
        if (!updateById(todo)) {
            throw new BusinessException("修改失败");
        }
    }

    @Override
    public void removeTodo(Long id) {
        if (!removeById(id)) {
            throw new BusinessException("删除失败");
        }
    }
}
