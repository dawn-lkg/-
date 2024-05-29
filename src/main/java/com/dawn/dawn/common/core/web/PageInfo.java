package com.dawn.dawn.common.core.web;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenliming
 * @date 2024/3/10 13:46
 */
@Data
public class PageInfo <T>{
    private int totalPage; // 总页数
    private int totalCount; // 总共有多少条数
    private int currentPage; // 当前是第几页
    private int currentCount; // 当前页面显示多少条数据
    private List<T> list = new ArrayList<T>();
}
