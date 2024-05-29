package com.dawn.dawn.common.core.exception;

import com.dawn.dawn.common.core.constant.Constants;

/**
 * @author chenliming
 * @date 2023/8/6 15:20
 */
public class BusinessException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private Integer code;

    public BusinessException() {
        this(Constants.RESULT_ERROR_MSG);
    }

    public BusinessException(String message) {
        this(Constants.RESULT_ERROR_CODE, message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
