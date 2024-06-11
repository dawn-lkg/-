package com.dawn.dawn.todo.service;

import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.todo.entity.Todo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.todo.vo.TodoVO;
import com.dawn.dawn.todo.param.TodoParam;
import com.dawn.dawn.todo.dto.TodoDto;

import java.util.List;

/**
 * (Todo)表服务接口
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:42
 */
public interface TodoService extends IService<Todo> {

     /**
      * 分页查询Todo记录
      * @param param 查询参数
      * @return Page<TodoVO>
      */
    CommonPage<TodoVO> pageRel(TodoParam param);
    
    /**
      * 查询所有的Todo记录
      * @param param 查询参数
      * @return List<TodoVO>
      */
    List<TodoVO> listRel(TodoParam param);
    
    /**
     * 根据id查询详情
     * @param id
     * @return TodoVO
     */
    TodoVO getByIdRel(String id);
    
    /**
     * 保存
     * @param dto dto
     */
    void saveTodo(TodoDto dto);
    
    /**
     * 修改
     * @param dto dto
     */
    void updateTodo(TodoDto dto);
    
    /**
     * 删除
     * @param id 主键id
     */
    void removeTodo(Long id);
}
