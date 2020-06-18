package com.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 10:46
 */
@SpringBootApplication
@ImportResource(locations={"classpath:consumer.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
