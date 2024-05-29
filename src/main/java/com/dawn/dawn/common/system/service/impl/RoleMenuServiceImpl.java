package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.system.mapper.RoleMenuMapper;
import com.dawn.dawn.common.system.entity.RoleMenu;
import com.dawn.dawn.common.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * (RoleMenu)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:12:57
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

