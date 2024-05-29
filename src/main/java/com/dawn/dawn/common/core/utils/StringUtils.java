package com.dawn.dawn.common.core.utils;

import com.dawn.dawn.common.core.constant.Constants;

/**
 * @author chenliming
 * @date 2024/3/19 23:32
 */
public class StringUtils {

    public static boolean isHttpUrl(String url) {
        return url.startsWith(Constants.HTTP) || url.startsWith(Constants.HTTPS);
    }
}
