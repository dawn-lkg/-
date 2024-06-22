package com.dawn.dawn.common.core.web;


import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.system.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author chenliming
 * @date 2023/8/6 0:45
 */
public class BaseController {

    /**
     * 获取当前登录用户user
     *
     * @return
     */
    public User getLoginUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                if (object instanceof User) {
                    return (User) object;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     *
     * @return userId
     */
    public String getLoginUserId() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

    /**
     * 返回成功
     *
     * @return Result
     */
    public Result<?> success() {
        return new Result<>(Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return Result
     */
    public Result<?> success(String message) {
        return new Result<>().setMessage(message);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @param <T>
     * @return Result
     */
    public <T> Result<T> success(T data) {
        return new Result<>(data, Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG);
    }

    /**
     * 返回成功
     *
     * @param data    返回数据
     * @param message 状态信息
     * @param <T>
     * @return Result
     */
    public <T> Result<T> success(T data, String message) {
        return new Result<>(data).setMessage(message);
    }

    /**
     * 返回失败
     *
     * @return Result
     */
    public Result<?> fail() {
        return new Result<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG);
    }

    /**
     * 返回失败
     *
     * @param data 返回失败
     * @param <T>
     * @return Result
     */
    public <T> Result<T> fail(T data) {
        return new Result<>(data, Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG);
    }

    /**
     * 返回失败
     *
     * @param message 失败状态信息
     * @return Result
     */
    public Result fail(String message) {
        return new Result().setMessage(message).setCode(Constants.RESULT_ERROR_CODE);
    }


}
