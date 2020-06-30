package com.omen.service.impl;

import com.omen.service.PayService;
import com.omen.service.UserService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
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
    private Redisson redisson;

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
        RLock rLock = redisson.getFairLock("lock:key:pay1");
        long threadId = Thread.currentThread().getId();
        try {
            boolean ref = true;
            rLock.lock(5000, TimeUnit.MILLISECONDS);
            if (ref) {
                Thread.sleep(300);
                System.out.println("执行了方法:" + threadId + ":" + name);
                rLock.unlock();
            } else {
                return "系统繁忙";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return threadId + "";
    }


    @Override
    public String rateLimiter(String index) {
        // 初始化
        RRateLimiter rateLimiter = redisson.getRateLimiter("myRateLimiter3");
        //有一个坑，如果不删除的话，初始配置是不会被覆盖的
//        rateLimiter.delete();
        // 最大流速 = 每1秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 5, 1, RateIntervalUnit.SECONDS);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(rateLimiter.tryAcquire()){
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(index);
                    }else{
                        System.out.println("没有令牌了");
                    }


                }
            }).start();
        }
        return index;
    }
    @Override
    public String rCountDownLath(String index) {

        RCountDownLatch rCountDownLatch=redisson.getCountDownLatch("test:countDownLath");
        rCountDownLatch.trySetCount(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                test1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test2();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test3();
            }
        }).start();
        try {
            rCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "完成";
    }

    private void test1(){
        RCountDownLatch rCountDownLatch=redisson.getCountDownLatch("test:countDownLath");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务1完成");
        rCountDownLatch.countDown();
    }
    private void test2(){
        RCountDownLatch rCountDownLatch=redisson.getCountDownLatch("test:countDownLath");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务2完成");
        rCountDownLatch.countDown();
    }
    private void test3(){
        RCountDownLatch rCountDownLatch=redisson.getCountDownLatch("test:countDownLath");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务3完成");
        rCountDownLatch.countDown();
    }
}
