package com.dawn.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 陈黎明
 */
@MapperScan("com.dawn.dawn.**.mapper")
@EnableTransactionManagement
@SpringBootApplication
@EnableWebSecurity
public class DawnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DawnApplication.class, args);
    }

}
