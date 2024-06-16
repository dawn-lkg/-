package com.dawn.dawn.novel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (NovelChapter)实体类
 *
 * @author 陈黎明
 * @since 2024-06-11 10:43:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("novel_chapter")
public class NovelChapter  implements Serializable {
    private static final long serialVersionUID = -85328681143634430L;

    private Integer id;

    private Integer novelId;

    private String title;

    private Integer number;

    private String content;

}

