package com.omen.service.impl;

import com.omen.service.PayService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */
@Service
public class PayServiceImpl implements PayService {

    @Value("${server.port}")
    private String port;

    @Override
    public String pay() {
        return "苹果手机"+port;
    }
}
