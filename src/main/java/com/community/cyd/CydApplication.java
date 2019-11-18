package com.community.cyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.community.cyd.mapper")
public class CydApplication {

    public static void main(String[] args) {
        SpringApplication.run(CydApplication.class, args);
    }

}
