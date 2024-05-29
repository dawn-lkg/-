package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.system.entity.UserRole;

/**
 * (UserRole)表服务接口
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:31
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 删除用户角色
     * @param userId
     */
    void deleteUserRole(String userId);
}

