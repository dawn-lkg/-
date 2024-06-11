package com.dawn.dawn.common.system.controller;


import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.param.OperationRecordParam;
import com.dawn.dawn.common.system.service.OperationRecordService;
import com.dawn.dawn.common.system.vo.OperationRecordVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SysOperationRecord)表控制层
 *
 * @author clm
 * @since 2023-10-18 22:07:06
 */
@RestController
@RequestMapping("/operation-record")
public class OperationRecordController extends BaseController {
    @Resource
    private OperationRecordService sysOperationRecordService;

    @GetMapping("page")
    public Result<?> page(OperationRecordParam param){
        param.setType(Constants.OPERATOR_TYPE_NORMAL);
        CommonPage<OperationRecordVo> operationRecordVoCommonPage = sysOperationRecordService.listPageRel(param);
        return success(operationRecordVoCommonPage);
    }

    @GetMapping("/loginOperator/page")
    public Result<?> loginPage(OperationRecordParam param){
        param.setType(Constants.OPERATOR_TYPE_LOGIN);
        CommonPage<OperationRecordVo> operationRecordVoCommonPage = sysOperationRecordService.listPageRel(param);
        return  success(operationRecordVoCommonPage);
    }

    @GetMapping("/record")
    public Result<?> getRecord(OperationRecordParam param){
        CommonPage<OperationRecordVo> operationRecordVoCommonPage = sysOperationRecordService.listByModule(param);
        return success(operationRecordVoCommonPage);
    }

}

