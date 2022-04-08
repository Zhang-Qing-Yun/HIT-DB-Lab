package com.qingyun.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description： 主启动类
 * @author: 張青云
 * @create: 2022-04-05 13:18
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.qingyun"})  // 去扫描该包下的注解
public class DBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class);
    }
}
