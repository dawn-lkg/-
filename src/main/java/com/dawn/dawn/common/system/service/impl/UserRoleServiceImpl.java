package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.system.mapper.UserRoleMapper;
import com.dawn.dawn.common.system.entity.UserRole;
import com.dawn.dawn.common.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:31
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void deleteUserRole(String userId) {
        remove(new LambdaQueryWrapper<>(UserRole.class)
                .eq(UserRole::getUserId, userId));
    }
}

