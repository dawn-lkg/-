package com.dawn.dawn.todo.controller;

import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.todo.service.TodoService;
import com.dawn.dawn.todo.vo.TodoVO;
import com.dawn.dawn.todo.param.TodoParam;
import com.dawn.dawn.todo.dto.TodoDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Todo)表控制层
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:39
 */
@RestController
@RequestMapping("todo")
public class TodoController extends BaseController {

    @Resource
    private TodoService todoService;

    @GetMapping("/page")
    public Result<?> page(TodoParam todoParam) {
        CommonPage<TodoVO> result = todoService.pageRel(todoParam);
        return success(result);
    }
    
    @GetMapping("/list")
    public Result<?> list(TodoParam todoParam) {
        List<TodoVO> result = todoService.listRel(todoParam);
        return success(result);
    }
    
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable String id) {
        // 数据查询
        TodoVO todovo = todoService.getByIdRel(id);
        return success(todovo);
    }
    
    @PostMapping
    public Result<?> save(@RequestBody TodoDto tododto) {
        tododto.setUserId(getLoginUserId());
        todoService.saveTodo(tododto);
        return success();
    }
    
    @PutMapping
    public Result<?> update(@RequestBody TodoDto tododto) {
        todoService.updateTodo(tododto);
        return success();
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        todoService.removeTodo(id);
        return success();
    }
}

