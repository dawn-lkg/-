package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.param.MenuParam;
import com.dawn.dawn.common.system.vo.RouterVo;

import java.util.List;

/**
 * (Menu)表服务接口
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:05
 */
public interface MenuService extends IService<Menu> {

    /**
     * 分页查询所有
     * @param param 查询所有
     * @return CommonPage<Menu>
     */
    CommonPage<Menu> pageRel(MenuParam param);
    /**
     * 查询所有
     * @param param 查询参数
     * @return List<Menu>
     */
    List<Menu> listRel(MenuParam param);

    /**
     * 构建前端路由所需要的菜单
     * @param menus 菜单列表
     * @return List<RouterVo>
     */
    List<RouterVo> buildMenus(List<Menu> menus,String menuId);

    /**
     * 根据用户id查询菜单
     * @param userId 用户id
     * @return List<Menu>
     */
    List<Menu> selectMenuByUserId(String userId);

    /**
     * 查询所有返回数
     * @param param 查询参数
     * @return List<Menu>
     */
    List<Menu> listTreeRel(MenuParam param);

    /**
     * 获取路由
     * @param roleId 角色id
     * @return List<MenuVo>
     */
    List<Menu> listMenuByRoleId(String roleId);
}

