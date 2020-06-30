package com.omen.service.impl;

import com.omen.service.PayService;
import com.omen.service.UserService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */
@Service
public class PayServiceImpl implements PayService {

    @Value("${server.port}")
    private String port;

    @Reference(check = false)
    private UserService userService;


    @Autowired
    private RedissonClient redissonClient;
    @Override
    public CompletableFuture<String> pay() {
        // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池
        return CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async pay1";
        });
    }

    @Override
    public String pay2(String name) {
        RLock rLock= redissonClient.getFairLock("lock:key:pay1");
        long threadId=Thread.currentThread().getId();
        try {
            boolean ref=true;
             rLock.lock(5000,TimeUnit.MILLISECONDS);
            if(ref){
                Thread.sleep(300);
                System.out.println("执行了方法:"+threadId+":"+name);
                rLock.unlock();
            }else{
                return "系统繁忙";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  threadId+"";
    }



}
