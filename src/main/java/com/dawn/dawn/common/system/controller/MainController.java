package com.dawn.dawn.common.system.controller;

import com.dawn.dawn.common.core.constant.Constants;
import com.dawn.dawn.common.core.constant.RedisConstants;
import com.dawn.dawn.common.core.utils.CommonUtil;
import com.dawn.dawn.common.core.utils.RedisCache;
import com.dawn.dawn.common.core.web.BaseController;
import com.dawn.dawn.common.core.web.Result;
import com.dawn.dawn.common.system.dto.LoginDto;
import com.dawn.dawn.common.system.entity.AccessTokenEntity;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.service.LoginService;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.vo.CaptchaVo;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chenliming
 * @date 2024/3/20 23:15
 */

@RestController
@RequestMapping
public class MainController extends BaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getRouters")
    public Result<?> getRouters(){
        List<Menu> menus = menuService.selectMenuByUserId(getLoginUserId());
        return success(menuService.buildMenus(menus, Constants.TOP_LEVEL_MENU));
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody @Valid LoginDto loginDto){
        loginService.verifyCode(loginDto);
        String jwt = loginService.login(loginDto);
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setToken(jwt);
        return success(accessTokenEntity);
    }

    @PostMapping("/logout")
    public Result<?> logout(){
        loginService.logout();
        return success("登出成功");
    }

    @GetMapping("/captcha")
    public Result<?> captcha(){
        SpecCaptcha specCaptcha = new SpecCaptcha(150, 45, 4);
        String base64 = specCaptcha.toBase64();
        String captchaValue = specCaptcha.text().toLowerCase();
        String key = CommonUtil.generateCaptchaText(6);
        redisCache.setCacheObject(RedisConstants.CAPTCHA+key,captchaValue,RedisConstants.CAPTCHA_EXPIRED, TimeUnit.SECONDS);
        CaptchaVo captchaVo = new CaptchaVo(base64,key);
        return success(captchaVo);
    }
}
