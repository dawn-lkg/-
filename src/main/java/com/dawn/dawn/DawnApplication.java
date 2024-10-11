package com.dawn.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author 陈黎明
 */
@EnableWebSocket
@MapperScan("com.dawn.dawn.**.mapper")
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
@EnableWebSecurity
public class DawnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DawnApplication.class, args);
    }

}
