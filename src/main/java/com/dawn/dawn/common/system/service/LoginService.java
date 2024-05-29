package com.dawn.dawn.common.system.service;


import com.dawn.dawn.common.system.dto.LoginDto;

/**
 * @author chenliming
 * @date 2023/8/6 11:20
 */
public interface LoginService {
    /**
     * 登录
     * @param loginDto 用户
     * @return jwt
     */
    String login(LoginDto loginDto);

    /**
     * 验证验证是否正确
     * @param loginDto dto
     */
    void verifyCode(LoginDto loginDto);

    /**
     * 登出
     */
    void logout();
}
