package com.dawn.dawn.common.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.utils.IpUtils;
import com.dawn.dawn.common.core.utils.JwtUtil;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.system.entity.User;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈黎明
 * @date 2024/10/4 下午11:59
 */
@Component
public class TokenService {
    @Value("${ip.xdbPath}")
    private String xdbPath;
    @Autowired
    private RedisCache redisCache;

    private static final int expireTime = 60;

    private static final long MILLIS_MINUTE= 60 * 1000L;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;


    public User getLoginUser(HttpServletRequest request){
        String token= JwtUtil.getAccessToken(request);
        if(StrUtil.isNotBlank(token)){
            Claims claims = null;
            try {
                claims = JwtUtil.parseJWT(token);
                String userId = claims.getSubject();
                User user =redisCache.getCacheObject(RedisConstants.LOGINUSER + userId);
                return user;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    public  void setUserAgent(User user){
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

    public void verifyToken(User user){
        long expireTime = user.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if(expireTime - currentTime <= MILLIS_MINUTE_TEN){
            refreshToken(user);
        }
    }

    public void refreshToken(User user){
        user.setExpireTime(System.currentTimeMillis()+expireTime*MILLIS_MINUTE);
        redisCache.setCacheObject(RedisConstants.LOGINUSER+user.getUserId(),user,expireTime, TimeUnit.MINUTES);
    }
}
