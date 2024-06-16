package com.dawn.dawn.novel.controller;



import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.novel.entity.NovelChapter;
import com.dawn.dawn.novel.param.NovelChapterParam;
import com.dawn.dawn.novel.service.NovelChapterService;
import com.dawn.dawn.novel.vo.NovelChapterVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (NovelChapter)表控制层
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:42
 */
@RestController
@RequestMapping("novelChapter")
public class NovelChapterController extends BaseController {
    @Resource
    private NovelChapterService novelChapterService;

    @GetMapping("/list")
    public Result<List<NovelChapterVO>> list(NovelChapterParam novelChapterParam) {
        List<NovelChapterVO> list = novelChapterService.listRel(novelChapterParam);
        return success(list);
    }

    @GetMapping("/content/{id}")
    public Result<?> content(@PathVariable Long id) {
        NovelChapter one = novelChapterService.getById(id);
        return success(one);
    }

    @GetMapping("/next")
    public Result<?> next(NovelChapterParam param){
        NovelChapter novelChapter = novelChapterService.nextChapterContent(param);
        return success(novelChapter);
    }

    @GetMapping("/previous")
    public Result<?> previous(NovelChapterParam param){
        NovelChapter novelChapter = novelChapterService.previousChapterContent(param);
        return success(novelChapter);
    }
}

