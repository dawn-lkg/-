package com.dawn.dawn.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.param.UserParam;
import com.dawn.dawn.common.system.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询
     * @param page 分页参数
     * @param userParam 查询参数
     * @return IPage<UserVo>
     */
    IPage<UserVo> selectPageRel(IPage<User> page, @Param("param") UserParam userParam);
}

