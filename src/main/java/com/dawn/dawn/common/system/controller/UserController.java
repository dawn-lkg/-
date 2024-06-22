package com.dawn.dawn.common.system.controller;

import com.dawn.dawn.common.core.aop.OperationLog;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.dto.UserDto;
import com.dawn.dawn.common.system.dto.UserPasswordDto;
import com.dawn.dawn.common.system.param.UserParam;
import com.dawn.dawn.common.system.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * (User)表控制层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @OperationLog(module = "用户",operator = "分页查询")
    @GetMapping("page")
    public Result page(UserParam userParam){
        System.out.println(userService.pageRel(userParam));
        return success(userService.pageRel(userParam));
    }

   // @OperationLog(module = "用户",operator = "查询用户信息")
    @GetMapping("info")
    public Result getUserInfo(){
        if(getLoginUser()==null){
            throw new BusinessException("没有登录");
        }
        return success(userService.getUserInfo(getLoginUserId()));
    }

    @OperationLog(module = "用户",operator = "添加用户")
    @PostMapping
    public Result<?> save(@RequestBody UserDto dto){
        userService.saveUser(dto);
        return success("新增成功");
    }

    @OperationLog(module = "用户",operator = "修改用户")
    @PutMapping
    public Result<?> update(@RequestBody UserDto dto){
        userService.updateUser(dto);
        return success("修改成功");
    }

    @OperationLog(module = "用户",operator = "删除用户")
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable String id){
        userService.deleteUser(id);
        return success("删除成功");
    }

    @OperationLog(module = "用户",operator = "修改头像")
    @PostMapping("/avatar")
    public Result<?> updateAvatar(MultipartFile file){
        String avatar = userService.updateAvatar(file);
        return success(avatar);
    }

    @OperationLog(module = "用户",operator = "修改用户信息")
    @PostMapping("/update-userInfo")
    public Result<?> updateUserInfo(@RequestBody UserDto dto){
        userService.updateUserInfo(dto);
        return success("修改成功");
    }

    @OperationLog(module = "用户",operator = "修改密码")
    @PostMapping("/update-password")
    public Result<?> updatePassword(@RequestBody UserPasswordDto dto){
        userService.updatePassword(dto);
        return success("修改成功");
    }
}

