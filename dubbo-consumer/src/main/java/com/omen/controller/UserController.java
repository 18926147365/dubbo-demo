package com.omen.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.omen.constant.SystemConst;
import com.omen.service.PayService;
import com.omen.service.UserService;
import com.omen.utils.RedisUtil;
import org.apache.dubbo.common.json.JSONArray;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 10:51
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Reference(version = UserService.version)
    private UserService userService;

    @Reference(version=PayService.version)
    private PayService payService;


    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("hello")
    public String hello(){

        CompletableFuture<String> helloFuture1 = payService.pay();
        // 为Future添加回调
        helloFuture1.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println(retValue);
            } else {
                exception.printStackTrace();
            }
        });
        return "123";
    }

    @RequestMapping("hello2")
    public String hello2(){
        RpcContext.getContext().setAttachment("names", "123456789");
        redisUtil.set(SystemConst.REDIS_ROUTE_RULE,"123");
        return payService.pay2("123");
    }

    @RequestMapping("hello3")
    public void hello3(){

    }
}
