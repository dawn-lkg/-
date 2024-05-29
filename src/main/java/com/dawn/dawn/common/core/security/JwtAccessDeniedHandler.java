package com.dawn.dawn.common.core.security;

import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.utils.CommonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenliming
 * @date 2024/5/25 下午1:16
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        CommonUtil.responseError(response, Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG);
    }
}
