package com.dawn.dawn.common.core.web.server;

import lombok.Data;

/**
 * @author chenliming
 * @date 2023/11/16 23:03
 */

@Data
public class Sys {
    /**
     * 服务器名称
     */
    private String computerName;
    /**
     * 服务ip
     */
    private String computerIp;
    /**
     * 项目路径
     */
    private String userDir;
    /**
     * 操作系统
     */
    private String osName;
    /**
     * 系统加工园
     */
    private String osArch;
}
