package com.dawn.dawn.common.system.entity;

import java.util.Date;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

/**
 * (Role)表实体类
 *
 * @author 陈黎明
 * @since 2024-03-10 15:07:52
 */
@Data
@TableName("sys_role")
public class Role extends Model<Role> {
    //角色id
    @TableId(type=IdType.AUTO)
    private String roleId;
    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态 （0正常 1停用)
    private String status;
    //备注
    private String remark;
    //创建人
    private String createBy;
    //更新人
    private String updateBy;
    //创建时间
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date updateTime;
    //逻辑删除
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<String>  menuIdList;
}

