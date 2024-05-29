package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.entity.Role;
import com.dawn.dawn.common.system.param.RoleParam;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author 陈黎明
 * @since 2024-03-10 01:20:27
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID获取角色列表
     * @param userId 用户ID
     * @return Role
     */
    List<Role> listRoleByUserId(String userId);


    /**
     * 分页查询角色列表
     * @param roleParam 查询参数
     * @return CommonPage<?>
     */
    CommonPage<?> pageRel(RoleParam roleParam);

    /**
     * 查询所有角色
     * @param param 查询参数
     * @return List<Role>
     */
    List<Role> listRel(RoleParam param);

    /**
     * 新增角色
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 修改角色
     * @param role 角色
     */
    void updateRole(Role role);

    /**
     * 删除角色
     * @param roleId 角色id
     */
    void deleteRole(String roleId);

}

