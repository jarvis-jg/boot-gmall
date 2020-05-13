package com.jarvis.gmall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "")
@SpringBootApplication
public class BootGmallSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGmallSearchServiceApplication.class, args);
    }

}
