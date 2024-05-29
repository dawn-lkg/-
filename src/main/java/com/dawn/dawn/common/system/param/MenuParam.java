package com.dawn.dawn.common.system.param;

import com.dawn.dawn.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author chenliming
 * @date 2024/3/14 21:06
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuParam extends BaseParam {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 父菜单ID
     */
    private Long parentId;
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
    private Integer isLink;
    /**
     * 是否缓存
     */
    private Integer isCache;
    /**
     * 菜单状态 0显示 1隐藏
     */
    private String hidden;
}
