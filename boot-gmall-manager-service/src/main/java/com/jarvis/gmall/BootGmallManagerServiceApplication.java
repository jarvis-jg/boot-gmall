package com.jarvis.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jarvis.gmall.manager.mapper")
public class BootGmallManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGmallManagerServiceApplication.class, args);
    }

}
