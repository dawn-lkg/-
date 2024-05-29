package com.dawn.dawn.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.common.system.entity.Role;
import com.dawn.dawn.common.system.param.RoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:20:27
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色
     * @param id 用户ID
     * @return
     */
    List<Role> listRoleByUserId(@Param("userId") String id);

    IPage<Role> selectPageRel(IPage<Role> page, @Param("param") RoleParam param);

    List<Role> selectListRel(@Param("param") RoleParam param);
}

