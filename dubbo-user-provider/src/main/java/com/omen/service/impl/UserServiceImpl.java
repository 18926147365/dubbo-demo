package com.omen.service.impl;

import com.omen.service.UserService;
import org.apache.dubbo.config.annotation.Service;


/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 8:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getName() {
        return "hello先生";
    }
}
