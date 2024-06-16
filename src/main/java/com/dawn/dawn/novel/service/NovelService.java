package com.dawn.dawn.novel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.novel.entity.Novel;
import com.dawn.dawn.novel.param.NovelParam;
import com.dawn.dawn.novel.vo.NovelVO;

import java.util.List;

/**
 * (Novel)表服务接口
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:18
 */
public interface NovelService extends IService<Novel> {


    /**
     * 查询所有小说
     * @param param 查询参数
     * @return List<NovelVO>
     */
    List<NovelVO> listRel(NovelParam param);
}

