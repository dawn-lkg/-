package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.dto.UserDto;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.entity.UserInfo;
import com.dawn.dawn.common.system.param.UserParam;

/**
 * (User)表服务接口
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户列表
     * @param param  查询参数
     * @return
     */
    CommonPage<?> pageRel(UserParam param);

    /**
     * 保存用户
     * @param userDto 用户dto
     */
    void saveUser(UserDto userDto);

    /**
     * 更新用户
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 根据账号获取用户
     * @param username 用户账号
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(String userId);

    /**
     * 是否存在用户名
     * @param userId 用户id
     * @param username 用户名
     */
    void isExitUsername(String username,String userId);

}

