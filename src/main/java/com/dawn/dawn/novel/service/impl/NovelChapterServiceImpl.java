package com.dawn.dawn.novel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.novel.mapper.NovelChapterMapper;
import com.dawn.dawn.novel.entity.NovelChapter;
import com.dawn.dawn.novel.param.NovelChapterParam;
import com.dawn.dawn.novel.service.NovelChapterService;
import com.dawn.dawn.novel.vo.NovelChapterVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * (NovelChapter)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:42
 */
@Service("novelChapterService")
public class NovelChapterServiceImpl extends ServiceImpl<NovelChapterMapper, NovelChapter> implements NovelChapterService {

    @Override
    public List<NovelChapterVO> listRel(NovelChapterParam param) {
        return baseMapper.selectListRel(param);
    }

    @Override
    public NovelChapter nextChapterContent(NovelChapterParam param) {
        if(Objects.isNull(param.getNovelId())||Objects.isNull(param.getNumber())){
            throw new BusinessException("参数错误");
        }
        NovelChapter novelChapter = lambdaQuery().eq(NovelChapter::getNovelId, param.getNovelId())
                .eq(NovelChapter::getNumber, param.getNumber()+1)
                .last(Constants.LIMITONESQL).one();
        if(Objects.isNull(novelChapter)){
            throw new BusinessException("章节不存在");
        }
        return novelChapter;
    }

    @Override
    public NovelChapter previousChapterContent(NovelChapterParam param) {
        if(Objects.isNull(param.getNovelId())||Objects.isNull(param.getNumber())){
            throw new BusinessException("参数错误");
        }
        NovelChapter novelChapter = lambdaQuery().eq(NovelChapter::getNovelId, param.getNovelId())
                .eq(NovelChapter::getNumber, param.getNumber()-1)
                .last(Constants.LIMITONESQL).one();
        if(Objects.isNull(novelChapter)){
            throw new BusinessException("章节不存在");
        }
        return novelChapter;
    }

}

