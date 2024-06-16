package com.dawn.dawn.common.system.entity;

import java.util.Collection;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * (User)表实体类
 *
 * @author 陈黎明
 * @since 2024-03-10 01:14:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("sys_user")
public class User implements UserDetails {
    //用户id
    @TableId(type = IdType.AUTO)
    private String userId;
    //用户名
    private String username;
    //用户昵称
    private String nickname;
    //密码
    private String password;
    //手机号
    private String phonenumber;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //性别 0男 1女 2未知
    private String sex;
    //账号状态 0正常 1停用
    private String status;
    //最后登录ip
    private String loginIp;
    //最后登录时间
    private Date loginDate;
    //创建人
    private String createBy;
    //更新人
    private String updateBy;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //逻辑删除
    /**
     * 是否删除，0表示正常，1表示删除
     */
    @JsonIgnore
    @TableLogic
    private Integer deleted;

    /**
     * 操作系统
     */
    @TableField(exist = false)
    private String os;
    /**
     * 浏览器
     */
    @TableField(exist = false)
    private String browser;
    /**
     * 登录信息
     */
    @TableField(exist = false)
    private String ipAddress;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

