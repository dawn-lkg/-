package com.dawn.dawn.common.system.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dawn.dawn.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenliming
 * @date 2024/5/20 下午6:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleParam extends BaseParam {
    //角色id
    @TableId(type= IdType.AUTO)
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
}
