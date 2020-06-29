package com.omen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.omen.entity.User;
import com.omen.mapper.UserMapper;
import com.omen.service.PayService;
import com.omen.service.UserService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    private UserMapper userMapper;

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
        List<User> list=userMapper.queryAll();

        return  "123"+ JSONObject.toJSONString(list);
    }
}
