package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.utils.CommonUtil;
import com.dawn.dawn.common.core.utils.SecurityUtils;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.dto.UserDto;
import com.dawn.dawn.common.system.dto.UserPasswordDto;
import com.dawn.dawn.common.system.entity.*;
import com.dawn.dawn.common.system.mapper.UserMapper;
import com.dawn.dawn.common.system.param.UserParam;
import com.dawn.dawn.common.system.service.FileService;
import com.dawn.dawn.common.system.service.RoleService;
import com.dawn.dawn.common.system.service.UserRoleService;
import com.dawn.dawn.common.system.service.UserService;
import com.dawn.dawn.common.system.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FileService fileService;

    @Override
    public CommonPage<?> pageRel(UserParam param) {
        IPage<UserVo> page = baseMapper.selectPageRel(new Page<>(param.getPageNum(), param.getPageSize()), param);
        page.getRecords().forEach(d->{
            List<Role> roles = roleService.listRoleByUserId(d.getUserId());
            List<String> roleIdList = roles.stream().map(Role::getRoleId).collect(Collectors.toList());
            StringBuilder roleNames=new StringBuilder();
            roles.stream().map(Role::getRoleName).collect(Collectors.toList()).forEach(item-> roleNames.append(item).append(","));
            if(roleNames.length()>0){
                roleNames.deleteCharAt(roleNames.length()-1);
            }
            d.setRoleIdList(roleIdList);
            d.setRoleNames(String.valueOf(roleNames));
        });
        return CommonPage.restPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDto userDto) {
        //检查用户名
        isExitUsername(userDto.getUsername(),null);
        //保存用户
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        String password = user.getPassword();
        user.setPassword(SecurityUtils.encryptPassword(password));
        if(!save(user)){
            throw new BusinessException("保存用户失败");
        }
        //保存角色
        List<String> roles = userDto.getRoleIdList();
        List<UserRole> userRoles = roles.stream().map(d -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(d);
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDto userDto) {
        //检查用户名
        isExitUsername(userDto.getUsername(),userDto.getUserId() );
        //更新用户
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        if(!updateById(user)){
            throw new BusinessException("更新用户失败");
        }
        //删除角色
        userRoleService.deleteUserRole(user.getUserId());
        //保存角色
        List<String> roles = userDto.getRoleIdList();
        List<UserRole> userRoles = roles.stream().map(d -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(d);
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String userId) {
        if(!removeById(userId)){
            throw new BusinessException("删除用户失败");
        }
        userRoleService.deleteUserRole(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        User user = getOne(lambdaQueryWrapper);
        if(Objects.nonNull(user)){
            //user.setRoles(roleService.listRoleByUserId(user.getId()));
            List<String> menuTypeList=new ArrayList<>();
            menuTypeList.add(Constants.MENU_TYPE_PERMISSION);
            //、user.setAuthorities(menuService.listMenuByUserId(user.getId(),menuTypeList));
        }
        return user;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        UserInfo userInfo=new UserInfo();
        User byId = getById(userId);
        BeanUtils.copyProperties(byId,userInfo);
        List<Role> roles = roleService.listRoleByUserId(byId.getUserId());
        List<String> roleList = roles.stream().map(Role::getRoleKey).collect(Collectors.toList());
        userInfo.setRoles(roleList);
        return userInfo;
    }

    @Override
    public void isExitUsername(String username,String userId) {
        List<User> list = lambdaQuery().eq(User::getUsername, username)
                .ne(Objects.nonNull(userId), User::getUserId, userId).list();
        if(!list.isEmpty()){
            throw new BusinessException("当前用户已经存在");
        }
    }

    @Override
    public User getUserByGithubId(Long githubId) {
        return lambdaQuery().eq(User::getGithubId, githubId).last(Constants.LIMITONESQL).one();
    }

    @Override
    public User saveUser(GitHubUser gitHubUser) {
        //创建用户
        User user = new User();
        user.setGithubId(gitHubUser.getId());
        user.setAvatar(gitHubUser.getAvatarUrl());
        user.setUsername(gengerUserName(gitHubUser.getLogin()));
        user.setNickname(CommonUtil.generateVisitorNameUsingRandomChars());
        user.setPassword(SecurityUtils.encryptPassword(Constants.DEFAULT_PASSWORD_VALUE));
        //保存用户
        if(!save(user)){
            throw new BusinessException("保存用户失败");
        }
        //保存角色
        Role role = roleService.getRoleByRoleKey(Constants.DEFAULT_ROLE_KEY);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(role.getRoleId());
        userRoleService.save(userRole);
        return user;
    }

    @Override
    public String updateAvatar(MultipartFile file) {
        //文件上传
        String avatar = fileService.uploadFile(file);
        //修改头像
        boolean update = lambdaUpdate().eq(User::getUserId, SecurityUtils.getUserId()).set(User::getAvatar, avatar).update();
        if(!update){
            throw new BusinessException("修改头像失败");
        }
        return avatar;
    }

    @Override
    public void updateUserInfo(UserDto dto) {
        boolean update = lambdaUpdate().eq(User::getUserId, SecurityUtils.getUserId())
                .set(User::getNickname, dto.getNickname())
                .set(User::getPhonenumber, dto.getPhonenumber())
                .set(User::getEmail, dto.getEmail())
                .update();
        if(!update){
            throw new BusinessException("修改用户信息失败");
        }
    }

    @Override
    public void updatePassword(UserPasswordDto userPasswordDto) {
        User loginUser = SecurityUtils.getLoginUser();
        if(!SecurityUtils.matchesPassword(userPasswordDto.getOldPassword(),loginUser.getPassword())){
            throw new BusinessException("修改密码失败,旧密码错误");
        }
        if(SecurityUtils.matchesPassword(userPasswordDto.getNewPassword(),loginUser.getPassword())){
            throw new BusinessException("修改密码失败,新密码不能和旧密码相同");
        }
        //更新密码
        boolean update = lambdaUpdate().set(User::getPassword, SecurityUtils.encryptPassword(userPasswordDto.getNewPassword())).update();
        if(!update){
            throw new BusinessException("更新密码失败");
        }
    }

    private String gengerUserName(String username) {
        User one = lambdaQuery().eq(User::getUsername, username).one();
        if(Objects.isNull(one)){
            return username;
        }
        for(int i=0;i<3;i++){
            String otherUsername=username+CommonUtil.generateCaptchaText(6);
            User user = lambdaQuery().eq(User::getUsername, username).one();
            if(Objects.isNull(user)){
                return otherUsername;
            }
        }
        throw new BusinessException("生成用户名失败");
    }

}

