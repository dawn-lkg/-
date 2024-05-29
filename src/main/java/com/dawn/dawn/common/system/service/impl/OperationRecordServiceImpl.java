package com.dawn.dawn.common.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.core.utils.IpUtils;
import com.dawn.dawn.common.core.web.CommonPage;
import com.dawn.dawn.common.system.entity.OperationRecord;
import com.dawn.dawn.common.system.mapper.OperationRecordMapper;
import com.dawn.dawn.common.system.param.OperationRecordParam;
import com.dawn.dawn.common.system.service.OperationRecordService;
import com.dawn.dawn.common.system.service.UserService;
import com.dawn.dawn.common.system.vo.OperationRecordVo;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * (SysOperationRecord)表服务实现类
 *
 * @author clm
 * @since 2023-10-18 22:07:06
 */
@Service("sysOperationRecordService")
public class OperationRecordServiceImpl extends ServiceImpl<OperationRecordMapper, OperationRecord> implements OperationRecordService {

    @Autowired
    private UserService userService;

    @Async
    @Override
    public void saveAsync(OperationRecord operationRecord) {
        if(!save(operationRecord)){
            throw new BusinessException("保存系统日志失败");
        }
    }

    @Override
    public CommonPage<OperationRecordVo> listPageRel(OperationRecordParam param) {
        IPage<OperationRecordVo> operationRecordVoIPage = baseMapper.selectPageRel(new Page<>(param.getPageNum(), param.getPageSize()), param);
        return CommonPage.restPage(operationRecordVoIPage);
    }

    @Override
    public void recordLogin(String username, String status, String message,String method) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        UserAgent userAgent= UserAgent.parseUserAgentString(requestAttributes.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        String osName = userAgent.getOperatingSystem().getName();
        String browser = userAgent.getBrowser().getName();
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setBrowser(browser);
        operationRecord.setIp(ip);
        operationRecord.setRequestMethod(Constants.REQUEST_TYPE_POST);
        operationRecord.setOs(osName);
        operationRecord.setTime(0L);
        operationRecord.setDescription(message);
        operationRecord.setStatus(status);
        operationRecord.setUsername(username);
        operationRecord.setType(Constants.OPERATOR_TYPE_LOGIN);
        operationRecord.setModule("登录");
        operationRecord.setMethod(method);
        operationRecord.setUrl("/login");
        saveAsync(operationRecord);
    }
}

