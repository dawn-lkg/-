package com.dawn.dawn.novel.controller;



import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.novel.param.NovelParam;
import com.dawn.dawn.novel.service.NovelService;
import com.dawn.dawn.novel.vo.NovelVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Novel)表控制层
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:12
 */
@RestController
@RequestMapping("novel")
public class NovelController extends BaseController {
    @Resource
    private NovelService novelService;

    @GetMapping("/list")
    public Result<?> list(NovelParam param) {
        List<NovelVO> list = novelService.listRel(param);
        return success(list);
    }
}

