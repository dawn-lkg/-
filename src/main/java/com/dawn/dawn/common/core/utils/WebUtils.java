package com.dawn.dawn.common.core.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chenliming
 * @date 2023/10/12 22:39
 */
public class WebUtils {

    public static void renderString(HttpServletResponse response,String s){
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
