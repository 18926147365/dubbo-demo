package com.omen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.omen.service.UserService;


/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 8:45
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Override
    public String getName() {
        return "hello先生";
    }
}
