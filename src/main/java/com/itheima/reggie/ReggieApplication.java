package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j // lombok提供的日志方法
@SpringBootApplication
@ServletComponentScan // 扫描过滤器
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        //Slf4j打印日志
        log.info("项目启动成功...");
    }
}
