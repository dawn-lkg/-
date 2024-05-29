package com.dawn.dawn.common.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2023/9/21 22:22
 */

@Data
@Accessors(chain = true)
public class AccessTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //token
    private String token;
}
