package com.dawn.dawn.common.core.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dawn.dawn.common.core.constant.Constants;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenliming
 * @date 2024/3/10 13:38
 */

@Data
public class CommonPage <T>{
    private Long page = Constants.DEFAULT_PAGE;
    private Long limit = Constants.DEFAULT_LIMIT;
    private Long total = 0L ;
    private List<T> list = new ArrayList<>();

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setPage(pageInfo.getPages());
        result.setLimit(pageInfo.getSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getRecords());
        return result;
    }
}
