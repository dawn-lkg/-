package com.dawn.dawn.novel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Novel)实体类
 *
 * @author 陈黎明
 * @since 2024-06-11 10:40:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("novel")
public class Novel  implements Serializable {
    private static final long serialVersionUID = 244371097297100267L;
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

