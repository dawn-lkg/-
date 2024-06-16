package com.dawn.dawn.common.system.vo;


import lombok.Data;

import java.util.Date;

/**
 * @author chenliming
 * @date 2024/1/9 22:57
 */

@Data
public class UserOnlineVo {
    /**
     * 主键
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 登录时间
     */
    private Date loginDate;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * ip归属地
     */
    private String ipAddress;
}
