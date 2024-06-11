package com.dawn.dawn.todo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dawn.dawn.todo.entity.Todo;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.todo.vo.TodoVO;
import com.dawn.dawn.todo.param.TodoParam;
import java.util.List;

/**
 * (Todo)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:45
 */
public interface TodoMapper extends BaseMapper<Todo> {

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param param 查询参数
     * @return List<TodoVO>
     */
    Page<TodoVO> selectPageRel(@Param("page") IPage<Todo> pageParam,
    @Param("param") TodoParam param);


    /**
     * 查询所有
     * @param param 查询参数
     * @return List<TodoVO>
     */
    List<TodoVO> selectListRel(@Param("param") TodoParam param);
}

