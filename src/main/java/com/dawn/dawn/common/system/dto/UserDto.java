package com.dawn.dawn.common.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenliming
 * @date 2023/9/7 20:43
 */

@Data
@Accessors(chain = true)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
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
    //用户类型（0管理员 1普通用户）
    private String userType;
    //创建人ID
    private String createBy;
    //角色列表
    private List<String> roleIdList;
}
