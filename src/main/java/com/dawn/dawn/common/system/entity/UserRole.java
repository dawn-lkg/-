package com.dawn.dawn.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * (UserRole)表实体类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:31
 */
@Data
@TableName("sys_user_role")
public class UserRole extends Model<UserRole> {
    //角色id
    private String userId;
    //角色id
    private String roleId;

    public UserRole(){}

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

