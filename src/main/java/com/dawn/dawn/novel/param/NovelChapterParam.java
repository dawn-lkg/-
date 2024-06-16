package com.dawn.dawn.novel.param;

import com.dawn.dawn.common.core.web.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (NovelChapter)Query
 *
 * @author 陈黎明
 * @since 2024-06-11 10:43:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class NovelChapterParam extends BaseParam {

    private Integer id;

    private Integer novelId;

    private String title;

    private Integer number;

    private String content;

}

