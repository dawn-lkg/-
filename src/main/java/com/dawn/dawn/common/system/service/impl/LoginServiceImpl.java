package com.dawn.dawn.common.system.service.impl;


import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.utils.IpUtils;
import com.dawn.dawn.common.core.utils.JwtUtil;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.core.utils.SecurityUtils;
import com.dawn.dawn.common.system.dto.LoginDto;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.entity.Role;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author chenliming
 * @date 2023/8/6 12:56
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${ip.xdbPath}")
    private String xdbPath;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisCache redisCache;
    @Autowired
    OperationRecordService operationRecordService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Override
    public String login(LoginDto loginDto) {
        User user = userService.getUserByUsername(loginDto.getUsername());
        if(user==null){
            operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_FAIL,"账号不存在",this.getClass().getName());
            throw new BusinessException("账号不存在");
        }
        if(!SecurityUtils.matchesPassword(loginDto.getPassword(),user.getPassword())){
            operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_FAIL,"密码错误",this.getClass().getName());
            throw new BusinessException("密码错误");
        }
        //设置角色
        setRoles(user);
        //设置权限
        setAuthorities(user);
        //设置操作信息
        setOperation(user);
        //保存到缓存
        redisCache.setCacheObject(RedisConstants.LOGINUSER+user.getUserId(),user,RedisConstants.LOGIN_EXPIRED, TimeUnit.MINUTES);
        //生成凭证
        String jwt= JwtUtil.createJWT(String.valueOf(user.getUserId()));
        operationRecordService.recordLogin(loginDto.getUsername(), Constants.SYSTEM_RECORD_SUCCESS,"登录成功",this.getClass().getName());
        return jwt;
    }

    /**
     * 设置操作基本信息
     * @param user 用户信息
     */
    public void setOperation(User user){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        UserAgent userAgent= UserAgent.parseUserAgentString(requestAttributes.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        String osName = userAgent.getOperatingSystem().getName();
        String browser = userAgent.getBrowser().getName();
        String ipAddress=IpUtils.getCityInfoByVectorIndex(ip,xdbPath);
        user.setLoginIp(ip);
        user.setLoginDate(new Date());
        user.setBrowser(browser);
        user.setIpAddress(ipAddress);
        user.setOs(osName);
    }

    /**
     * 设置角色
     * @param user 用户信息
     */
    public void setRoles(User user){
        List<Role> roles = roleService.listRoleByUserId(user.getUserId());
        user.setRoles(roles);
    }

    /**
     * 设置权限
     * @param user 用户信息
     */
    public void setAuthorities(User user){
        List<Menu> menus = menuService.selectMenuByUserId(user.getUserId());
        user.setAuthorities(menus);
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
        String key = RedisConstants.LOGINUSER + loginUser.getUsername();
        if(redisCache.getCacheObject(key)!=null){
            redisCache.deleteObject(key);
        }

    }
}
