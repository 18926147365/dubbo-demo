package com.omen;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 8:45
 */
@SpringBootApplication()
@ImportResource(locations={"classpath:user-provider.xml"})
@DubboComponentScan(basePackages = "com.omen.service")
public class UserApplication {

    public static void main(String[] args) {
            SpringApplication.run(UserApplication.class,args);
    }
}
