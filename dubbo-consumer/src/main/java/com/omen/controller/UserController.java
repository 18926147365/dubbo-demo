package com.omen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.omen.service.PayService;
import com.omen.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 10:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private PayService payService;

    @RequestMapping("hello")
    public String hello(){
        return  userService.getName()+"购买了"+payService.pay();
    }
}
