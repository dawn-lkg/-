package com.dawn.dawn.common.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author chenliming
 * @date 2024/3/19 21:18
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterVo {
    /**
     * 主键id
     */
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
     * 是否显示图标
     */
    private Boolean showTagIcon;
    /**
     * 图标
     */
    private String iconName;

    private List<RouterVo> childs;
}
