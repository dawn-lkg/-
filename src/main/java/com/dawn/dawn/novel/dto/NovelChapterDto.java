package com.dawn.dawn.novel.dto;

import lombok.Data;

/**
 * (NovelChapter)Dto
 *
 * @author 陈黎明
 * @since 2024-06-11 10:43:05
 */
@Data
public class NovelChapterDto{


    private Integer id;

    private Integer novelId;

    private String title;

    private Integer number;

    private String content;

}
