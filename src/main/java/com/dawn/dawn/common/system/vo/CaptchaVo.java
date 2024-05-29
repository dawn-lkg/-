package com.dawn.dawn.common.system.vo;

import lombok.Data;

/**
 * @author chenliming
 * @date 2024/5/26 上午12:58
 */

@Data
public class CaptchaVo {
    /**
     * 验证码图片
     */
    private String captchaSvg;
    /**
     * 验证码key
     */
    private String captchaCode;

    public CaptchaVo(){}

    public CaptchaVo(String captchaSvg, String captchaCode) {
        this.captchaSvg = captchaSvg;
        this.captchaCode = captchaCode;
    }
}
