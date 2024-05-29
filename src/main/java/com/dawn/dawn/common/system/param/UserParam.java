package com.dawn.dawn.common.system.param;

import com.dawn.dawn.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenliming
 * @date 2023/8/20 22:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserParam extends BaseParam {
    //用户id
    private String userId;
    //用户名
    private String username;
    //用户名密码
    private String password;
    //昵称
    private String nickname;
    //账号状态 0正常 1停用
    private String status;
    //性别
    private String sex;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //头像
    private String avatar;
}
