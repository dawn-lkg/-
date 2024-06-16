package com.dawn.dawn.novel.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dawn.dawn.novel.entity.Novel;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.novel.vo.NovelVO;
import com.dawn.dawn.novel.param.NovelParam;
import java.util.List;

/**
 * (Novel)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-06-11 10:49:39
 */
public interface NovelMapper extends BaseMapper<Novel> {

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param param 查询参数
     * @return List<NovelVO>
     */
    Page<NovelVO> selectPageRel(@Param("page") IPage<Novel> pageParam,
    @Param("param") NovelParam param);


    /**
     * 查询所有
     * @param param 查询参数
     * @return List<NovelVO>
     */
    List<NovelVO> selectListRel(@Param("param") NovelParam param);
}

