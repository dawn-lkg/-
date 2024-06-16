package com.dawn.dawn.novel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.novel.entity.NovelChapter;
import com.dawn.dawn.novel.param.NovelChapterParam;
import com.dawn.dawn.novel.vo.NovelChapterVO;

import java.util.List;

/**
 * (NovelChapter)表服务接口
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:42
 */
public interface NovelChapterService extends IService<NovelChapter> {

    /**
     * 查询所有小说章节
     * @param param 查询参数
     * @return List<NovelChapterVO>
     */
    List<NovelChapterVO> listRel(NovelChapterParam param);

    /**
     * 下一章内容
     * @param param 查询参数
     * @return NovelChapter
     */
    NovelChapter nextChapterContent(NovelChapterParam param);

    /**
     * 上一章内容
     * @param param 查询参数
     * @return NovelChapter
     */
    NovelChapter previousChapterContent(NovelChapterParam param);
}

