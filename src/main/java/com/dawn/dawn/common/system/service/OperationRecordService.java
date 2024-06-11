package com.dawn.dawn.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.entity.OperationRecord;
import com.dawn.dawn.common.system.param.OperationRecordParam;
import com.dawn.dawn.common.system.vo.OperationRecordVo;


/**
 * (SysOperationRecord)表服务接口
 *
 * @author clm
 * @since 2023-10-18 22:07:06
 */
public interface OperationRecordService extends IService<OperationRecord> {


    /**
     * 保存系统日志
     * @param operationRecord
     */
    void saveAsync(OperationRecord operationRecord);

    /**
     * 分页查询
     * @param param
     * @return
     */
    CommonPage<OperationRecordVo> listPageRel(OperationRecordParam param);

    /**
     * 记录登录日志
     */
    void recordLogin(String username,String status,String message,String module);

    /**
     * 查询所有日志
     * @param param 查询参数
     * @return CommonPage<OperationRecordVo
     */
    CommonPage<OperationRecordVo> listByModule(OperationRecordParam param);
}

