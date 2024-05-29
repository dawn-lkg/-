package com.dawn.dawn.common.system.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Menu)实体类
 *
 * @author 陈黎明
 * @since 2024-05-07 23:42:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu {
    private static final long serialVersionUID = -57021846169653468L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private String id;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 父菜单ID
     */
    private String parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 菜单简介
     */
    private String content;
    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 是否外链
     */
    private Boolean isLink;
    /**
     * 是否缓存
     */
    private Boolean isCache;
    /**
     * 菜单状态 0显示 1隐藏
     */
    private Boolean hidden;
    /**
     * 图标
     */
    private String iconName;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private List<Menu> childs;

}

