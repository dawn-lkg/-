package com.dawn.dawn.common.system.service.impl;


import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.utils.JwtUtil;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.core.utils.SecurityUtils;
import com.dawn.dawn.common.system.dto.LoginDto;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.LoginService;
import com.dawn.dawn.common.system.service.OperationRecordService;
import com.dawn.dawn.common.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author chenliming
 * @date 2023/8/6 12:56
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisCache redisCache;
    @Autowired
    OperationRecordService operationRecordService;
    @Override
    public String login(LoginDto loginDto) {
        User byUsername = userService.getUserByUsername(loginDto.getUsername());
        if(byUsername==null){
            operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_FAIL,"账号不存在",this.getClass().getName());
            throw new BusinessException("账号不存在");
        }
        if(!SecurityUtils.matchesPassword(loginDto.getPassword(),byUsername.getPassword())){
            operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_FAIL,"密码错误",this.getClass().getName());
            throw new BusinessException("密码错误");
        }
        redisCache.setCacheObject(RedisConstants.LOGINUSER+byUsername.getUserId(),byUsername);
        String jwt= JwtUtil.createJWT(String.valueOf(byUsername.getUserId()));
        operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_SUCCESS,"登录成功",this.getClass().getName());
        return jwt;
    }

    @Override
    public void verifyCode(LoginDto loginDto) {
        String verifyCode = loginDto.getVerifyCode();
        String verifyText = loginDto.getVerifyText();
        String resultCode =redisCache.getCacheObject(RedisConstants.CAPTCHA + verifyCode);
        if(Objects.isNull(resultCode)){
            throw new BusinessException("验证码过期");
        }
        if(!verifyText.equalsIgnoreCase(resultCode)){
            throw new BusinessException("验证码不正确");
        }
    }

    @Override
    public void logout() {
        User loginUser = SecurityUtils.getLoginUser();
        redisCache.deleteObject(RedisConstants.LOGINUSER+loginUser.getUsername());
    }
}
