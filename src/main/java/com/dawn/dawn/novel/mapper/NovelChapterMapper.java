package com.dawn.dawn.novel.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dawn.dawn.novel.entity.NovelChapter;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.novel.vo.NovelChapterVO;
import com.dawn.dawn.novel.param.NovelChapterParam;
import java.util.List;

/**
 * (NovelChapter)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-06-11 10:49:53
 */
public interface NovelChapterMapper extends BaseMapper<NovelChapter> {

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param param 查询参数
     * @return List<NovelChapterVO>
     */
    Page<NovelChapterVO> selectPageRel(@Param("page") IPage<NovelChapter> pageParam,
    @Param("param") NovelChapterParam param);


    /**
     * 查询所有
     * @param param 查询参数
     * @return List<NovelChapterVO>
     */
    List<NovelChapterVO> selectListRel(@Param("param") NovelChapterParam param);
}

