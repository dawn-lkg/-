package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.mapper.MenuMapper;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.param.MenuParam;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.vo.RouterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (Menu)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:21:05
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public CommonPage<Menu> pageRel(MenuParam param) {
        IPage<Menu> menuPage = baseMapper.selectPageRel(new Page<>(param.getPageNum(), param.getPageSize()), param);
        return CommonPage.restPage(menuPage);
    }

    @Override
    public List<Menu> listRel(MenuParam param) {
        return baseMapper.selectListRel(param);
    }

    @Override
    public List<Menu> selectMenuByUserId(String userId) {
        return baseMapper.selectMenuByUserId(userId);
    }

    @Override
    public List<Menu> listTreeRel(MenuParam param) {
        List<Menu> menus = baseMapper.selectListRel(param);
        return toTree(menus, Constants.TOP_LEVEL_MENU);
    }

    @Override
    public List<Menu> listMenuByRoleId(String roleId) {
        List<Menu> menus = baseMapper.listMenuByRoleId(roleId);
        return menus;
    }

    public List<Menu> toTree(List<Menu> list,String menuId){
        return list.stream().filter(menu -> menu.getParentId().equals(menuId))
                .map(menu -> {
                    Menu menuVo= new Menu();
                    BeanUtils.copyProperties(menu,menuVo);
                    menuVo.setParentId(getParentId(menu));
                    menuVo.setChilds(toTree(list, menu.getId()));
                    return menuVo;
                }).sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());
    }

    @Override
    public List<RouterVo> buildMenus(List<Menu> menus, String menuId) {
        return menus.stream().filter(menu -> menu.getParentId().equals(menuId))
                .map(menu -> {
                    RouterVo routerVo = new RouterVo();
                    routerVo.setName(getMenuName(menu));
                    routerVo.setTitle(menu.getTitle());
                    routerVo.setIconName(menu.getIconName());
                    routerVo.setContent(menu.getContent());
                    routerVo.setIsCache(menu.getIsCache());
                    routerVo.setHidden(getHidden(menu));
                    routerVo.setPath(menu.getPath());
                    routerVo.setIsCache(menu.getIsCache());
                    routerVo.setShowTagIcon(getShowTagIcon(menu));
                    routerVo.setIsLink(getIsLink(menu));
                    routerVo.setChilds(buildMenus(menus,menu.getId()));
                    return routerVo;
                }).collect(Collectors.toList());
    }

    public String getMenuName(Menu menu){
        return Optional.of(menu).map(Menu::getName).orElse(null);
    }

    public Boolean getHidden(Menu menu){
        if(Objects.nonNull(menu.getHidden())){
            return menu.getHidden();
        }
        return null;
    }

    public Boolean getShowTagIcon(Menu menu){
        if(menu.getParentId().equals(Constants.TOP_LEVEL_MENU)){
            return true;
        }
        return null;
    }

    public String getParentId(Menu menu){
        if(menu.getParentId().equals(Constants.TOP_LEVEL_MENU)){
            return null;
        }
        return menu.getParentId();
    }

    public Boolean getIsLink(Menu menu){
        if(menu.getIsLink()){
            return true;
        }
        return null;
    }
}

