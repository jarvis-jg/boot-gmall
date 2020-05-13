package com.jarvis.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jarvis.gmall.user.mapper")
public class BootGmallUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGmallUserServiceApplication.class, args);
    }

}
