package com.omen.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.SpringProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 18:00
 */
@Order(0) // @Order注解可以改变执行顺序，越小越先执行
@Component
public class InitApplication implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SpringProperties.setProperty("phahahah.version","1.0.8");
        System.err.println(SpringProperties.getProperty("phahahah.version"));
    }
}
