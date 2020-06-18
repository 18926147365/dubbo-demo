package com.omen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.omen.service.PayService;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */
@Service
public class PayServiceImpl implements PayService {

    @Override
    public String pay() {
        return "苹果手机";
    }
}
