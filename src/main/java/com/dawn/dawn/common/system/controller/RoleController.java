package com.dawn.dawn.common.system.controller;

import com.dawn.dawn.common.core.aop.OperationLog;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.entity.Role;
import com.dawn.dawn.common.system.param.RoleParam;
import com.dawn.dawn.common.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * (Role)表控制层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:20:27
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @OperationLog(module = "角色",operator = "分页查询")
    @GetMapping("/page")
    public Result<?> page(RoleParam roleParam){
        CommonPage<?> commonPage = roleService.pageRel(roleParam);
        return success(commonPage);
    }

    @OperationLog(module = "角色",operator = "查询所有")
    @GetMapping("/list")
    public Result<?> list(RoleParam roleParam){
        List<Role> roles = roleService.listRel(roleParam);
        return success(roles);
    }

    @OperationLog(module = "角色",operator = "添加角色")
    @PostMapping
    public Result<?> save(@RequestBody Role role){
        roleService.saveRole(role);
        return success("添加角色成功");
    }

    @OperationLog(module = "角色",operator = "更新角色")
    @PutMapping
    public Result<?> update(@RequestBody Role role){
        roleService.updateRole(role);
        return success("修改角色成功");
    }

    @OperationLog(module = "角色",operator = "删除角色")
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable String id){
        roleService.deleteRole(id);
        return success("删除成功");
    }
}

