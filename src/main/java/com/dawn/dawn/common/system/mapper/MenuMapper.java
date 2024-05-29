package com.dawn.dawn.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.param.MenuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:05
 */

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 分页查询
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<Menu>
     */
    IPage<Menu> selectPageRel(IPage<User> page, @Param("param") MenuParam param);

    /**
     * 查询所有
     * @param param 查询参数
     * @return List<Menu>
     */
    List<Menu> selectListRel(@Param("param") MenuParam param);

    /**
     * 根据用户id查询菜单
     * @param userId 用户id
     * @return List<Menu>
     */
    List<Menu> selectMenuByUserId(String userId);

    /**
     * 根据角色id获取菜单
     * @param roleId 角色id
     * @return List<Menu>
     */
    List<Menu> listMenuByRoleId(@Param("roleId") String roleId);
}

