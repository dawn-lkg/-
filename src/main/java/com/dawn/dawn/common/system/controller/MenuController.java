package com.dawn.dawn.common.system.controller;



import com.dawn.dawn.common.core.aop.OperationLog;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.param.MenuParam;
import com.dawn.dawn.common.system.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:05
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;

    @OperationLog(module = "菜单",operator = "分页查询")
    @GetMapping("/list")
    public Result<?>  list(MenuParam param){
        List<Menu> menus = menuService.listTreeRel(param);
        return success(menus);
    }

    @OperationLog(module = "菜单",operator = "添加菜单")
    @PostMapping
    public Result<?> save(@RequestBody Menu menu){
        if(!menuService.save(menu)){
            return fail("新增失败");
        }
        return success(menu);
    }

    @OperationLog(module = "菜单",operator = "更新菜单")
    @PutMapping
    public Result<?> update(@RequestBody Menu menu){
        if(!menuService.updateById(menu)){
            return fail("修改失败");
        }
        return success(menu);
    }

    @OperationLog(module = "菜单",operator = "删除菜单")
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable Long id){
        if(!menuService.removeById(id)){
            return fail("删除失败");
        }
        return success();
    }
}

