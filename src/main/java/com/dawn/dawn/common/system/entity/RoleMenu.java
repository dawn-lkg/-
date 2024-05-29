package com.dawn.dawn.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * (RoleMenu)表实体类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:12:57
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu extends Model<RoleMenu> {
    //角色id
    private String roleId;
    //菜单id
    private String menuId;

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}

