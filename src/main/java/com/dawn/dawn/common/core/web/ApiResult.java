package com.dawn.dawn.common.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2024/3/10 1:10
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult <T> implements Serializable {
    private static final long serialVersionUID=1L;
    public static final int SUCCESS=0;
    public static final int FAIL=1;
    private T data;
    private String message;
    private int code;
    private String error;
    public ApiResult(){

    }
    public ApiResult(T data,int code,String message){
        this.setData(data);
        this.setMessage(message);
        this.setCode(code);
    }
    public ApiResult (T data){
        this(data,SUCCESS,null);
    }
    public ApiResult(String message){
        this(null,SUCCESS,message);
    }
    public ApiResult(Integer status,String message){
        this(null,status,message);
    }

}
