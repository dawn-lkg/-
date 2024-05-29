package com.dawn.dawn.common.system.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenliming
 * @date 2023/9/4 23:21
 */
@Data
@Accessors(chain = true)
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    //用户名
    private String username;
    //用户名密码
    private String password;
    //昵称
    private String nickname;
    //账号状态 0正常 1删除
    private String status;
    //性别
    private String sex;
    //邮箱
    private String email;
    //手机号
    private String phonenumber;
    //头像
    private String avatar;
    //创建人ID
    private Long createBy;
    //角色列表
    private String roleNames;
    //创建时间
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;
    //角色列表
    private List<String> roleIdList;
}
