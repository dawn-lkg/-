package com.dawn.dawn.todo.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Todo)实体类
 *
 * @author 陈黎明
 * @since 2024-06-01 23:58:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("todo")
public class Todo  implements Serializable {
    private static final long serialVersionUID = -15510135027433171L;
    /**
    * 主键id
    */
    @TableId(type = IdType.AUTO)
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

