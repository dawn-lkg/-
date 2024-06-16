package com.dawn.dawn.novel.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Novel)VO
 *
 * @author 陈黎明
 * @since 2024-06-11 10:40:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NovelVO{
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 小说标题
    */
    private String title;
    /**
    * 封面
    */
    private String coverImage;
    /**
    * 作者
    */
    private String author;
    /**
     * 描述
     */
    private String description;
}

