package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.entity.RoleMenu;
import com.dawn.dawn.common.system.mapper.RoleMapper;
import com.dawn.dawn.common.system.entity.Role;
import com.dawn.dawn.common.system.param.RoleParam;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.service.RoleMenuService;
import com.dawn.dawn.common.system.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Role)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:20:27
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private MenuService menuService;

    @Override
    public List<Role> listRoleByUserId(String userId) {
        return baseMapper.listRoleByUserId(userId);
    }

    @Override
    public CommonPage<?> pageRel(RoleParam roleParam) {
        IPage<Role> roleIPage = baseMapper.selectPageRel(new Page<>(roleParam.getPageNum(), roleParam.getPageSize()), roleParam);
        roleIPage.getRecords().forEach(d->{
            List<Menu> menus =menuService.listMenuByRoleId(d.getRoleId());
            List<String> ids = menus.stream().map(Menu::getId).collect(Collectors.toList());
            d.setMenuIdList(ids);
        });
        CommonPage<Role> roleCommonPage = CommonPage.restPage(roleIPage);
        return roleCommonPage;
    }

    @Override
    public List<Role> listRel(RoleParam param) {
        return baseMapper.selectListRel(param);
    }

    @Override
    public void saveRole(Role role) {
        if(!save(role)){
            throw new BusinessException("新增角色失败");
        }
        List<RoleMenu> roleMenuList = role.getMenuIdList()
                .stream().map(d -> new RoleMenu(role.getRoleId(), d)).collect(Collectors.toList());
        if(!roleMenuService.saveBatch(roleMenuList)){
            throw  new BusinessException("新增角色失败");
        }
    }

    @Override
    public void updateRole(Role role) {
        if(!updateById(role)){
            throw new BusinessException("修改角色失败");
        }
        //删除菜单角色
        roleMenuService.remove(new LambdaQueryWrapper<>(RoleMenu.class).eq(RoleMenu::getRoleId,role.getRoleId()));

        List<RoleMenu> roleMenuList = role.getMenuIdList()
                .stream().map(d -> new RoleMenu(role.getRoleId(), d)).collect(Collectors.toList());
        if(!roleMenuService.saveBatch(roleMenuList)){
            throw new BusinessException("新增角色失败");
        }
    }

    @Override
    public void deleteRole(String roleId) {
        if(!removeById(roleId)){
            throw new BusinessException("删除失败");
        }
    }

    @Override
    public Role getRoleByRoleKey(String roleKey) {
        return lambdaQuery().eq(Role::getRoleKey, roleKey).last(Constants.LIMITONESQL).one();
    }
}

