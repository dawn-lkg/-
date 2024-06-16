package com.dawn.dawn.novel.param;

import com.dawn.dawn.common.core.web.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (Novel)Query
 *
 * @author 陈黎明
 * @since 2024-06-11 10:44:42
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class NovelParam extends BaseParam {
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

