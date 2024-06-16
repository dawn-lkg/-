package com.dawn.dawn.common.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.vo.UserOnlineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author chenliming
 * @date 2024/1/9 22:23
 */

@RestController
@RequestMapping("/monitor/online")
public class UserOnlineController extends BaseController {
    @Autowired
    private RedisCache redisCache;


    @GetMapping("/list")
    public Result<?> online(){
        Collection<String> keys = redisCache.keys(RedisConstants.LOGINUSER + "*");
        List<UserOnlineVo> userOnlineList = new ArrayList<>();
        for (String key : keys)
        {
            UserOnlineVo userOnlineVo = new UserOnlineVo();
            User user = redisCache.getCacheObject(key);
            BeanUtil.copyProperties(user,userOnlineVo);
            userOnlineList.add(userOnlineVo);
        }
        return success(userOnlineList);
    }

    @PostMapping("/offline")
    public Result<?> offline(@RequestBody List<String> idList){
        idList.forEach(d->{
            String key = RedisConstants.LOGINUSER + d;
            if(Objects.nonNull(redisCache.getCacheObject(key))){
                redisCache.deleteObject(key);
            }
        });
        return success("下线成功");
    }
}
