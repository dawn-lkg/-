package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.dto.UserDto;
import com.dawn.dawn.common.system.dto.UserPasswordDto;
import com.dawn.dawn.common.system.entity.GitHubUser;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.entity.UserInfo;
import com.dawn.dawn.common.system.param.UserParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * (User)表服务接口
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户列表
     * @param param  查询参数
     * @return
     */
    CommonPage<?> pageRel(UserParam param);

    /**
     * 保存用户
     * @param userDto 用户dto
     */
    void saveUser(UserDto userDto);

    /**
     * 更新用户
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 根据账号获取用户
     * @param username 用户账号
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(String userId);

    /**
     * 是否存在用户名
     * @param userId 用户id
     * @param username 用户名
     */
    void isExitUsername(String username,String userId);

    /**
     * 根据githubid查询用户
     * @param githubId githubid
     * @return User
     */
    User getUserByGithubId(Long githubId);

    /**
     * 保存github用户
     * @param gitHubUser github用户
     * @return User
     */
    User saveUser(GitHubUser gitHubUser);

    /**
     *
     * @param file 文件
     * @return String
     */
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户信息
     * @param dto 用户信息
     */
    void updateUserInfo(UserDto dto);

    /**
     * 修改密码
     * @param userPasswordDto 密码dto
     */
    void updatePassword(UserPasswordDto userPasswordDto);
}

