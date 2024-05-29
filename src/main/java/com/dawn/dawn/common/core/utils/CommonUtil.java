package com.dawn.dawn.common.core.utils;

import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.web.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author chenliming
 * @date 2024/3/20 23:00
 */
public class CommonUtil {

    /**
     * 支持跨域
     *
     * @param response HttpServletResponse
     */
    public static void addCrossHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", Constants.TOKEN_HEADER_NAME);
    }

    /**
     * 获取文件后缀
     * @param fileName 文件名
     * @return
     */
    public static String getFileExtension(String fileName){
        int lastDotIndex=fileName.lastIndexOf(".");
        if(lastDotIndex>=0){
            return fileName.substring(lastDotIndex+1).toLowerCase();
        }
        return "";
    }

    /**
     * 输出错误信息
     *
     * @param response HttpServletResponse
     * @param code     错误码
     * @param message  提示信息
     */
    public static void responseError(HttpServletResponse response, Integer code, String message) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.toJSONString(new Result<>(null, code, message)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成key
     * @param num
     * @return
     */
    public static String generateCaptchaText(int num) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
