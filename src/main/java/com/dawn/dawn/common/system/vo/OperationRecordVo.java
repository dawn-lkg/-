package com.dawn.dawn.common.system.vo;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenliming
 * @date 2023/10/19 19:47
 */

@Data
public class OperationRecordVo implements Serializable {
    //主键id
    @TableId(type = IdType.AUTO)
    private String id;
    //用户名
    private String username;
    //用户名称
    private String nickname;
    //操作耗时
    private Long time;
    //操作模块
    private String module;
    //操作模块描述
    private String description;
    //请求地址
    private String url;
    //请求方式
    private String requestMethod;
    //调用方法
    private String method;
    //操作方法
    private String os;
    //浏览器类型
    private String browser;
    //ip地址
    private String ip;
    //ip归属地
    private String ipAddress;
    //状态 0成功 1异常
    private String status;
    //创建时间
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date updateTime;
    //逻辑删除 0正常 1删除
    private Integer deleted;
}
