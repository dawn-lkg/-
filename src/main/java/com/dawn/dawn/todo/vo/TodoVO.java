package com.dawn.dawn.todo.vo;

import java.util.Date;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Todo)VO
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TodoVO{
    /**
    * 主键id
    */
    private String id;
    /**
    * 待办名称
    */
    private String name;
    /**
    * 所属用户
    */
    private String userId;
    /**
    * 待办描述
    */
    private String description;
    /**
    * 是否通知
    */
    private Integer isNotify;
    /**
    * 通知时间
    */
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date notifyTime;
    /**
    * 待办时间
    */
    @JsonFormat(pattern = DatePattern.NORM_DATE_PATTERN)
    private Date time;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 创建人
    */
    private Long createBy;
    /**
    * 逻辑删除
    */
    private Integer deleted;

}

