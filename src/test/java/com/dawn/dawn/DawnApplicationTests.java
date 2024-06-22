package com.dawn.dawn;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dawn.dawn.common.core.utils.EmailSender;
import com.dawn.dawn.common.core.utils.CommonUtil;
import com.dawn.dawn.common.core.utils.IpUtils;
import com.dawn.dawn.common.system.entity.Menu;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.param.MenuParam;
import com.dawn.dawn.common.system.param.OperationRecordParam;
import com.dawn.dawn.common.system.param.UserParam;
import com.dawn.dawn.common.system.service.MenuService;
import com.dawn.dawn.common.system.service.OperationRecordService;
import com.dawn.dawn.common.system.service.UserService;
import com.dawn.dawn.common.system.service.impl.GitHubOAuthService;
import com.dawn.dawn.novel.entity.NovelChapter;
import com.dawn.dawn.novel.param.NovelChapterParam;
import com.dawn.dawn.novel.service.NovelChapterService;
import com.dawn.dawn.novel.service.NovelService;
import com.dawn.dawn.rabbitmq.constant.RabbitMqConstant;
import com.dawn.dawn.todo.entity.Todo;
import com.dawn.dawn.todo.param.TodoParam;
import com.dawn.dawn.todo.service.TodoService;
import com.wf.captcha.SpecCaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

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
    void test() {
        User user = new User();
        user.setUsername("admin");
        user.setNickname("����Ա");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("2360187899@qq.com");
        user.setPhonenumber("18827574648");
        System.out.println(userService.save(user));
    }

    @Test
    void test1() {
        UserParam userParam = new UserParam();
        userParam.setPageNum(1);
        userParam.setPageSize(10);
        System.out.println(userService.pageRel(userParam));
    }

    @Test
    void test2() {
//        List<Menu> menus = menuService.listRel(new MenuParam());
//        System.out.println(menus);
//        menuService.buildMenus(menus,0L).forEach(d->{
//            System.out.println(d);
//        });
        // System.out.println(menuService.buildMenus(menuService.selectMenuByUserId(1L), 0L));
        System.out.println(menuService.list());
    }

    @Test
    void test3() {
        System.out.println(userService.list(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, "admin")));
        // System.out.println(userService.list());
    }

    @Test
    void test4() {
        List<Menu> menus = menuService.listRel(new MenuParam());
        System.out.println(menus);
        System.out.println(menuService.buildMenus(menus, "0"));

    }

    @Test
    void test5() {
        SpecCaptcha specCaptcha = new SpecCaptcha(150, 45, 4);
        System.out.println(specCaptcha.toBase64());
        //System.out.println(specCaptcha.text().toLowerCase());

    }

    @Test
    void test6() {
        System.out.println(CommonUtil.generateCaptchaText(6));
    }

    @Autowired
    private OperationRecordService operationRecordService;

    @Test
    void test7() {
        OperationRecordParam operationRecordParam = new OperationRecordParam();
        // operationRecordParam.setType("1");
        System.out.println(operationRecordService.listPageRel(operationRecordParam));
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test8() {
        User user = new User();
        user.setUsername("admin");
        user.setNickname("管理员");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("2360187899@qq.com");
        user.setPhonenumber("18827574648");
        //rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.key.test", user);
    }

    //RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }


    public static int ticket = 10000;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dawn";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Test
    public void test9() throws SQLException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 200; i++) {

            Thread thread = new Thread(() -> {
//                synchronized (this){
                reentrantLock.lock();
                try {
                    processTicket();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantLock.unlock();
                    countDownLatch.countDown();
                }
//                }
            });
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("结果："+ticket);
        long end = System.currentTimeMillis();
        System.out.println((end - start));

    }

    private void processTicket() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        try {
            connection.setAutoCommit(false);

            // 悲观锁
            String selectForUpdateSql = "SELECT ticket FROM test WHERE id = 1";
            PreparedStatement selectStatement = connection.prepareStatement(selectForUpdateSql);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int remainingTickets = resultSet.getInt("ticket");

                if (remainingTickets > 0) {
                    String updateSql = "UPDATE test SET ticket = ticket - 1 WHERE id = 1";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.executeUpdate();
                    System.out.println("Ticket sold, remaining: " + (remainingTickets - 1));
                } else {
                    System.out.println("No tickets remaining");
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Test
    public void test10() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t1执行");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("t2执行");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("t3执行");
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }

    @Test
    public void test11() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(3); // 需要减少的计数器数量

            Thread t1 = new Thread(() -> {
                try {
                    System.out.println("T1 start");
                    // 模拟耗时操作
                    Thread.sleep(1000);
                    System.out.println("T1 end");
                    latch.countDown(); // 计数器减1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    //latch.await(); // 等待计数器减到0
                    System.out.println("T2 start");
                    // 模拟耗时操作
                    Thread.sleep(1000);
                    System.out.println("T2 end");
                    latch.countDown(); // 计数器减1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread t3 = new Thread(() -> {
                try {
                    latch.await(); // 等待计数器减到0
                    System.out.println("T3 start");
                    // 模拟耗时操作
                    Thread.sleep(1000);
                    System.out.println("T3 end");
                    latch.countDown(); // 计数器减1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();
            t3.start();

    }

    @Autowired
    EmailSender emailSender;

    @Test
    public void test12(){
        emailSender.sendEmail("2360187899@qq.com","标题","<h1>测试标题</h1>");
    }

    @Test
    public void test13(){
        Todo todo = new Todo();
        todo.setId("1");
        todo.setName("测试");
        todo.setUserId("1");
        todo.setNotifyTime(new Date());
        todo.setDescription("发送时间："+ DateUtil.date().toTimeStr());
        rabbitTemplate.convertAndSend(RabbitMqConstant.MAIL_MESSAGE_EXCHANGE, RabbitMqConstant.MAIL_MESSAGE_ROUTING_KEY,
                todo, message -> {
                    //设置消息持久化
                    MessageProperties props = message.getMessageProperties();
                    props.setHeader("x-delay", 30000);
//                    props.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//                    props.setExpiration(String.valueOf(6000));
                    return message;
                });
    }

    @Autowired
    TodoService todoService;

    @Test
    public void test14(){
        TodoParam todoParam = new TodoParam();
        todoParam.setTimeStart("2024-06-01 ");
        todoParam.setTimeEnd("2024-06-02");
        System.out.println(todoService.listRel(todoParam));

    }

    @Test
    public void test15(){
        // 创建两个日期实例
        Date date1 = DateUtil.parse("2023-01-01 08:00:00");
        Date date2 = DateUtil.parse("2023-01-01 08:01:00");
        System.out.println(DateUtil.betweenMs(date2, date1));
    }

    @Autowired
    private NovelService novelService;
    @Autowired
    private NovelChapterService novelChapterService;

    @Test
    public void test16(){
        List<NovelChapter> list = novelChapterService.list();
        for (NovelChapter novelChapter : list) {
            System.out.println(novelChapter);
        }
    }

    @Test
    public void test17(){
        NovelChapterParam novelChapterParam = new NovelChapterParam();
        novelChapterParam.setNovelId(3);
        novelChapterParam.setNumber(4);
        System.out.println(novelChapterService.previousChapterContent(novelChapterParam));
    }

    @Autowired
    private IpUtils ipUtils;

    @Test
    public void test18(){
        System.out.println(ipUtils.getCityInfoByVectorIndex("117.152.89.247","D:\\project\\project\\dawn\\src\\main\\resources\\static\\ip\\csdn-ip2region.xdb"));
    }

    @Test
    public void test19(){
        Map<String, Object> data = new HashMap<>();
        data.put("client_id", "Ov23liT1TPjwEDKfRrOO");
        data.put("client_secret", "0b16bb9973f5cb973a658e543474e696991d6e90");
        data.put("code", "3e00031e59f13e1612bc");
        //        使用HttpUtil发送post请求
        String post = HttpRequest.post("https://github.com/login/oauth/access_token")
                .header(Header.ACCEPT, "application/json")
                .body(JSONUtil.toJsonStr(data))
                .execute().body();
        System.out.println(JSONObject.parseObject(post));
        String accessToken = JSONObject.parseObject(post).getString("access_token");
        String tokenType=JSONObject.parseObject(post).getString("token_type");
        String user=HttpRequest.get("https://api.github.com/user")
                .header("Authorization", "token "+accessToken)
                .execute().body();
        System.out.println(JSONObject.parseObject(user));
    }

    @Autowired
    private GitHubOAuthService gitHubOAuthService;

    @Test
    public void test20(){
        String body = HttpRequest.get("https://github.com/login/oauth/authorize?client_id=Ov23li09raH4jFrLkpMY&redirect_uri=http://120.27.215.0:8283/callback&scope=user:email").execute().body();
        System.out.println(body);
//        JSONObject gitHubAccessToken = gitHubOAuthService.getGitHubAccessToken("4bae333126f24ac4f440");
//        String accessToken = gitHubAccessToken.getString("access_token");
//        System.out.println(gitHubOAuthService.getGitHubUser(accessToken));

    }
}
