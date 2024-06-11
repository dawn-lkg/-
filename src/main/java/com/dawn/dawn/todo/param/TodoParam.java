package com.dawn.dawn.todo.param;

import java.util.Date;

import com.dawn.dawn.common.core.web.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class TodoParam extends BaseParam {
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
    private Date notifyTime;
    /**
    * 待办时间
    */
    private String time;
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
    /**
     * 筛选开始时间
     */
    private String timeStart;
    /**
     * 筛选结束时间
     */
    private String timeEnd;
}

