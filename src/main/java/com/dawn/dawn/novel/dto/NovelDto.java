package com.dawn.dawn.novel.dto;

import lombok.Data;

/**
 * (Novel)Dto
 *
 * @author 陈黎明
 * @since 2024-06-11 10:40:39
 */
@Data
public class NovelDto{

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

}
