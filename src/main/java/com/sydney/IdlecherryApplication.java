package com.sydney;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sydney.dao")
public class IdlecherryApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdlecherryApplication.class, args);
    }

}
