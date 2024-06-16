package com.dawn.dawn.novel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.novel.mapper.NovelMapper;
import com.dawn.dawn.novel.entity.Novel;
import com.dawn.dawn.novel.param.NovelParam;
import com.dawn.dawn.novel.service.NovelService;
import com.dawn.dawn.novel.vo.NovelVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Novel)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-06-11 10:35:19
 */
@Service("novelService")
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {

    @Override
    public List<NovelVO> listRel(NovelParam param) {
        return baseMapper.selectListRel(param);
    }
}

