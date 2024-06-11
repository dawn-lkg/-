package com.dawn.dawn.weblink.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Weblink)表实体类
 *
 * @author 陈黎明
 * @since 2024-05-27 13:37:26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Weblink extends Model<Weblink> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String url;
    /**
     * 简介
     */
    private String content;
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
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 逻辑删除
     */
    private Integer deleted;
}

