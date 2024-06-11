package com.dawn.dawn.todo.dto;

import java.util.Date;
import lombok.Data;

/**
 * (Todo)Dto
 *
 * @author 陈黎明
 * @since 2024-06-01 23:59:50
 */
@Data
public class TodoDto{

     /**
     * 主键id
     */
    private String id;
     /**
     * 待办名称
     */
    private String name;
     /**
     * 所属用户
     */
    private String userId;
     /**
     * 待办描述
     */
    private String description;
     /**
     * 是否通知
     */
    private Integer isNotify;
     /**
     * 通知时间
     */
    private Date notifyTime;
     /**
     * 待办时间
     */
    private Date time;
     /**
     * 创建时间
     */
    private Date createTime;
     /**
     * 更新时间
     */
    private Date updateTime;
     /**
     * 创建人
     */
    private Long createBy;
     /**
     * 逻辑删除
     */
    private Integer deleted;

}
