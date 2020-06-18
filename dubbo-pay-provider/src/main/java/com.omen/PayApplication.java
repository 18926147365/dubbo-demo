package com.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 16:55
 */
@SpringBootApplication()
@ImportResource(locations={"classpath:pay-provider.xml"})
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
