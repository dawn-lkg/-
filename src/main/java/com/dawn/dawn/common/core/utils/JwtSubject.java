package com.dawn.dawn.common.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2023/8/12 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String username;
}
