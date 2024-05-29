package com.dawn.dawn.common.core.web;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenliming
 * @date 2023/8/20 22:18
 */
@Data
public class BaseParam implements Serializable {
    private static final long serialVersionUID=1L;

    @TableField(exist = false)
    private int pageNum;

    @TableField(exist = false)
    private int pageSize;
}
