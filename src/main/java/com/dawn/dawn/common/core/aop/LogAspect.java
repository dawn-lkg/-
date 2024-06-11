package com.dawn.dawn.common.core.aop;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.utils.HttpContextUtils;
import com.dawn.dawn.common.system.entity.OperationRecord;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.OperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author chenliming
 * @date 2023/10/18 20:13
 */

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Resource
    private OperationRecordService operationRecordService;

    // 参数、返回结果、错误信息等最大保存长度
    private static final int MAX_LENGTH = 1000;
    // 用于记录请求耗时
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.dawn.dawn.common.core.aop.OperationLog)")
    public void logPointCut(){}

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "logPointCut()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result){
        saveLog(joinPoint,result,null);
    }

    @AfterThrowing(value = "logPointCut()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Exception e){
        saveLog(joinPoint,null,e);
    }


    private void recordLog(ProceedingJoinPoint joinPoint, long time){
        MethodSignature signature=(MethodSignature)  joinPoint.getSignature();
        Method method= signature.getMethod();
        OperationLog operationLog =method.getAnnotation(OperationLog.class);
        log.info("============log start==========");
        log.info("module:{}", operationLog.module());
        log.info("operation:{}", operationLog.operator());

        String className=joinPoint.getTarget().getClass().getName();
        String methodName=signature.getName();
        log.info("request method:{}",className+"."+methodName+"()");

        Object[] args=joinPoint.getArgs();
        String param= JSON.toJSONString(args[0]);
        log.info("param:{}",param);

        HttpServletRequest request= HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}",request.getRemoteAddr());
        log.info("请求耗时：{}",time/1000.0);
        log.info("============log end==========");
    }

    /**
     * 保存操作日志
     * @param joinPoint 切面
     * @param result 结果
     * @param e 异常
     */
    private void saveLog(JoinPoint joinPoint,Object result,Exception e){
        OperationRecord operationRecord=new OperationRecord();
        //记录操作耗时
        if(startTime.get()!=null){
            operationRecord.setTime((System.currentTimeMillis()-startTime.get()));
        }
        //记录当前登录用户
        User user=getLoginUser();
        if(Objects.nonNull(user)){
            operationRecord.setUsername(user.getUsername());
            operationRecord.setNickname(user.getNickname());
        }
        //记录请求地址、请求方式、ip
        HttpServletRequest request= HttpContextUtils.getHttpServletRequest();
        if(Objects.nonNull(request)){
            operationRecord.setUrl(request.getRequestURI());
            operationRecord.setRequestMethod(request.getMethod());
            UserAgent ua= UserAgentUtil.parse(ServletUtil.getHeaderIgnoreCase(request,"User-Agent"));
            operationRecord.setOs(ua.getPlatform().toString());
            operationRecord.setBrowser(ua.getBrowser().toString());
            operationRecord.setIp(ServletUtil.getClientIP(request));
        }
        //记录异常
        if(Objects.nonNull(e)){
            operationRecord.setStatus(Constants.SYSTEM_RECORD_FAIL);
        }

        //记录模块，操作功能，请求方法，请求参数，返回结果
        MethodSignature signature=(MethodSignature)  joinPoint.getSignature();
        operationRecord.setMethod(joinPoint.getTarget().getClass().getName()+"."+signature.getName());
        Method method= signature.getMethod();
        if(Objects.nonNull(method)){
            OperationLog operationLog =method.getAnnotation(OperationLog.class);
            if(Objects.nonNull(operationLog)){
                operationRecord.setModule(operationLog.module());
                operationRecord.setDescription(operationLog.operator());
            }
        }
        operationRecordService.saveAsync(operationRecord);
    }

    /**
     * 获取当前登录用户
     * @return
     */
    private User getLoginUser(){
        Authentication subject= SecurityContextHolder.getContext().getAuthentication();
        if(subject!=null){
            Object object=subject.getPrincipal();
            if(object instanceof User){
                return (User) object;
            }
        }
        return null;
    }
}
