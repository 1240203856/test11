package com.icbc.cdcp.pssp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.icbc.cdcp.pssp.mapper"}) //指定扫描接口和映射配置文件的包名
@EnableTransactionManagement(proxyTargetClass = true) //开启事务管理功能
public class PsspApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PsspApplication.class, args);
    }
}
