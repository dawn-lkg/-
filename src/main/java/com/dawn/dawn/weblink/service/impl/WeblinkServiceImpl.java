package com.dawn.dawn.weblink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dawn.dawn.weblink.mapper.WeblinkMapper;
import com.dawn.dawn.weblink.entity.Weblink;
import com.dawn.dawn.weblink.service.WeblinkService;
import org.springframework.stereotype.Service;

/**
 * (Weblink)表服务实现类
 *
 * @author 陈黎明
 * @since 2024-05-27 13:37:27
 */
@Service("weblinkService")
public class WeblinkServiceImpl extends ServiceImpl<WeblinkMapper, Weblink> implements WeblinkService {

}

