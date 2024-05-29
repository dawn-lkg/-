package com.dawn.dawn;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dawn.dawn.rabbitmq.config.RabbitMQConfig;
import com.dawn.dawn.common.core.utils.CommonUtil;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.param.MenuParam;
import com.dawn.dawn.common.system.param.OperationRecordParam;
import com.dawn.dawn.common.system.param.UserParam;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.service.OperationRecordService;
import com.dawn.dawn.common.system.service.UserService;
import com.wf.captcha.SpecCaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DawnApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
        System.out.println(userService.list());
    }

    @Test
    void test(){
        User user = new User();
        user.setUsername("admin");
        user.setNickname("����Ա");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("2360187899@qq.com");
        user.setPhonenumber("18827574648");
        System.out.println(userService.save(user));
    }

    @Test
    void test1(){
        UserParam userParam = new UserParam();
        userParam.setPageNum(1);
        userParam.setPageSize(10);
        System.out.println(userService.pageRel(userParam));
    }

    @Test
    void test2(){
//        List<Menu> menus = menuService.listRel(new MenuParam());
//        System.out.println(menus);
//        menuService.buildMenus(menus,0L).forEach(d->{
//            System.out.println(d);
//        });
       // System.out.println(menuService.buildMenus(menuService.selectMenuByUserId(1L), 0L));
        System.out.println(menuService.list());
    }

    @Test
    void test3(){
        System.out.println(userService.list(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, "admin")));
       // System.out.println(userService.list());
    }

    @Test
    void test4(){
        List<Menu> menus = menuService.listRel(new MenuParam());
        System.out.println(menus);
        System.out.println(menuService.buildMenus(menus, "0"));

    }

    @Test
    void test5(){
        SpecCaptcha specCaptcha = new SpecCaptcha(150, 45, 4);
        System.out.println(specCaptcha.toBase64());
        //System.out.println(specCaptcha.text().toLowerCase());

    }

    @Test
    void test6(){
        System.out.println(CommonUtil.generateCaptchaText(6));
    }

    @Autowired
    private OperationRecordService operationRecordService;

    @Test
    void test7(){
        OperationRecordParam operationRecordParam = new OperationRecordParam();
       // operationRecordParam.setType("1");
        System.out.println(operationRecordService.listPageRel(operationRecordParam));
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test8(){
        User user = new User();
        user.setUsername("admin");
        user.setNickname("管理员");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("2360187899@qq.com");
        user.setPhonenumber("18827574648");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.key.test", user);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
