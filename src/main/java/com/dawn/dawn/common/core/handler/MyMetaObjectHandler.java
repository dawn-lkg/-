package com.dawn.dawn.common.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dawn.dawn.common.core.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 陈黎明
 * @since 2022/7/24 15:55
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]....");
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("createBy", SecurityUtils.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]....");
        long id=Thread.currentThread().getId();
        log.info("线程id为：{}",id);
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("update_by",SecurityUtils.getUserId());
    }
}
