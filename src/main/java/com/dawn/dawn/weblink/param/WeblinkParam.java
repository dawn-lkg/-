package com.dawn.dawn.weblink.param;

import java.util.Date;

import com.dawn.dawn.common.core.web.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Weblink)Param
 *
 * @author 陈黎明
 * @since 2024-05-27 13:44:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeblinkParam extends BaseParam {

    /**
     * 主键id
     */
    private Long id;
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
    private String createBy;
    /**
     * 逻辑删除 0正常 1删除
     */
    private Integer deleted;

}

