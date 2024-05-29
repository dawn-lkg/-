package com.dawn.dawn.common.system.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author chenliming
 * @date 2024/3/23 15:51
 */

@Data
public class LoginDto {
    /**
     * 登录账号
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 登录密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String verifyText;

    /**
     * 验证key
     */
    @NotNull(message = "验证码不能为空")
    private String verifyCode;
}
