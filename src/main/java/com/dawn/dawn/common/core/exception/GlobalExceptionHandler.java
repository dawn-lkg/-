package com.dawn.dawn.common.core.exception;

import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.utils.CommonUtil;
import com.dawn.dawn.common.core.web.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenliming
 * @date 2023/8/6 15:25
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                     HttpServletResponse response){
        CommonUtil.addCrossHeaders(response);
        return new Result(Constants.RESULT_ERROR_CODE,"请求方式不正确");
    }
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> accessDeniedExceptionHandler(AccessDeniedException e, HttpServletResponse response) {
        CommonUtil.addCrossHeaders(response);
        return new Result<>(Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG).setError(e.toString());
    }
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e, HttpServletResponse response) {
        CommonUtil.addCrossHeaders(response);
        return new Result<>(e.getCode(), e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result<?> exceptionHandler(Throwable e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        CommonUtil.addCrossHeaders(response);
        return new Result<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG).setError(e.toString());
    }

    /**
     * 参数校验异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<?> bindExceptionHandler(Exception e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        CommonUtil.addCrossHeaders(response);
        List<FieldError> fieldErrors = null;
        if (e instanceof BindException) {
            fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
        } else if (e instanceof MethodArgumentNotValidException) {
            fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        } else {
            return new Result<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG);
        }
        List<String> allError = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            allError.add(error.getDefaultMessage());
        }
        return new Result<>(Constants.RESULT_ERROR_CODE).setMessage(String.valueOf(allError));
    }

}
