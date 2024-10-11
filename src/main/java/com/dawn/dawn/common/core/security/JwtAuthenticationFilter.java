package com.dawn.dawn.common.core.security;

import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.impl.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
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
    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User loginUser = tokenService.getLoginUser(request);

        if(!Objects.isNull(loginUser)){
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken usernamePasswordCredentials = new UsernamePasswordAuthenticationToken(loginUser, null,loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordCredentials);
        }

        filterChain.doFilter(request,response);

        //        if(StrUtil.isNotBlank(token)){
//            try {
//                Claims claims = JwtUtil.parseJWT(token);
//                String userId = claims.getSubject();
//                User user;
//                user =redisCache.getCacheObject(RedisConstants.LOGINUSER + userId);
//                if(Objects.isNull(user)){
//                    Result<Object> objectResult = new Result<>(Constants.RESULT_NOT_LOGUIN,Constants.RESULT_ERROR_MSG);
//                    WebUtils.renderString(response, JSONUtil.toJSONString(objectResult));
//                    return;
////                    user= userService.getById(userId);
////                    List<String> menuTypeList = new ArrayList<>();
////                    menuTypeList.add(Constants.MENU_TYPE_PERMISSION);
////                    List<Menu> authorities = menuService.listMenuByUserId(userId, menuTypeList);
////                    user.set(authorities);
//                }
//
//            } catch (Exception e) {
//                log.error(e.getMessage());
//                Result<Object> objectResult = new Result<>(Constants.RESULT_NOT_LOGUIN,Constants.RESULT_ERROR_MSG);
//                WebUtils.renderString(response, JSONUtil.toJSONString(objectResult));
//                return;
//            }
//        }
    }
}
