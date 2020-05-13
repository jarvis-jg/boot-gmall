package com.jarvis.gmall.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 项目启动类
 * @author jarvis
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jarvis.gmall.user.mapper")
public class BootGmallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGmallUserApplication.class, args);
    }

}
