package com.dawn.dawn.common.core.security;

import cn.hutool.core.util.StrUtil;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.utils.JSONUtil;
import com.dawn.dawn.common.core.utils.JwtUtil;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.core.utils.WebUtils;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author chenliming
 * @date 2024/3/23 17:36
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token= JwtUtil.getAccessToken(request);
        if(StrUtil.isNotBlank(token)){
            try {
                Claims claims = JwtUtil.parseJWT(token);
                String userId = claims.getSubject();
                User user;
                user =redisCache.getCacheObject(RedisConstants.LOGINUSER + userId);
                if(Objects.isNull(user)){
                    Result<Object> objectResult = new Result<>(Constants.RESULT_NOT_LOGUIN,Constants.RESULT_ERROR_MSG);
                    WebUtils.renderString(response, JSONUtil.toJSONString(objectResult));
                    return;
//                    user= userService.getById(userId);
//                    List<String> menuTypeList = new ArrayList<>();
//                    menuTypeList.add(Constants.MENU_TYPE_PERMISSION);
//                    List<Menu> authorities = menuService.listMenuByUserId(userId, menuTypeList);
//                    user.set(authorities);
                }
                UsernamePasswordAuthenticationToken usernamePasswordCredentials = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordCredentials);
            } catch (Exception e) {
                log.error(e.getMessage());
                Result<Object> objectResult = new Result<>(Constants.RESULT_NOT_LOGUIN,Constants.RESULT_ERROR_MSG);
                WebUtils.renderString(response, JSONUtil.toJSONString(objectResult));
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
