package com.qingyun.db.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-08 21:40
 **/
@Configuration
@MapperScan("com.qingyun.db.mapper")
public class DBConfig {
}
