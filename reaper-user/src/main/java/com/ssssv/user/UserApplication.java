package com.ssssv.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.ssssv.*.mapper", "com.ssssv.*.dao"})
@ComponentScan(value = "com.ssssv")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
