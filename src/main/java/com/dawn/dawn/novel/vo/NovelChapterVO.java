package com.dawn.dawn.novel.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (NovelChapter)VO
 *
 * @author 陈黎明
 * @since 2024-06-11 10:43:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NovelChapterVO{

    private Integer id;

    private Integer novelId;

    private String title;

    private Integer number;

}

