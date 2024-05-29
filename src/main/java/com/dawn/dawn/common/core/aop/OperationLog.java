package com.dawn.dawn.common.core.aop;

import java.lang.annotation.*;

/**
 * @author chenliming
 * @date 2023/10/18 20:08
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @ interface OperationLog {
    String module() default "";
    String operator() default "";
}
