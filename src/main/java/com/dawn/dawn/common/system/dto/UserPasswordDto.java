package com.dawn.dawn.common.system.dto;

import lombok.Data;

/**
 * @author chenliming
 * @date 2024/6/23 上午12:57
 */
@Data
public class UserPasswordDto {
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
}
