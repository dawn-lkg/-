package com.dawn.dawn.common.core.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2023/8/20 22:18
 */
@Data
public class BaseParam implements Serializable {
    private static final long serialVersionUID=1L;

    private int pageNum;

    private int pageSize;

    private String createTimeStart;

    private String createTimeEnd;

}
