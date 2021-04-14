package org.lbq.wrsboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lbq.wrsboot.mapper")
public class WrsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WrsBootApplication.class, args);
    }

}
