package com.dawn.dawn.common.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author 陈黎明
 * @date 2024/10/3 上午12:31
 */

public class WebSocketUtils {

    public static boolean isWebSocketRequest(HttpServletRequest request){
        //websocket 请求必须是GET方式
        if(!"GET".equalsIgnoreCase(request.getMethod())){
            return false;
        }
        // 检查"Upgrade"头部是否为"websocket"
        String upgradeHeader = request.getHeader("Upgrade");
        if (upgradeHeader == null || !"websocket".equalsIgnoreCase(upgradeHeader.trim())) {
            return false;
        }

        // 检查"Connection"头部是否包含"Upgrade"
        List<String> connectionHeaders = Collections.list(request.getHeaders("Connection"));
        if (!connectionHeaders.contains("Upgrade")) {
            return false;
        }

        // 检查"Sec-WebSocket-Version"头部是否存在
        String versionHeader = request.getHeader("Sec-WebSocket-Version");
        if (versionHeader == null || versionHeader.isEmpty()) {
            return false;
        }

        // 检查"Sec-WebSocket-Key"头部是否存在
        String keyHeader = request.getHeader("Sec-WebSocket-Key");
        if (keyHeader == null || keyHeader.isEmpty()) {
            return false;
        }

        return true;
    }
}
