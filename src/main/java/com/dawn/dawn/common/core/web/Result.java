package com.dawn.dawn.common.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2023/8/5 23:53
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID=1L;
    public static final int SUCCESS=0;
    public static final int FAIL=1;
    private T data;
    private String message;
    private int code;
    private String error;
    public Result(){

    }
    public Result(T data, int code, String message){
        this.setData(data);
        this.setMessage(message);
        this.setCode(code);
    }
    public Result(T data){
        this(data,SUCCESS,null);
    }
    public Result(String message){
        this(null,SUCCESS,message);
    }
    public Result(Integer status, String message){
        this(null,status,message);
    }

}
