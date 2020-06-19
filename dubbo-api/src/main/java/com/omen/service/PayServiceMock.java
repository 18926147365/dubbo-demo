package com.omen.service;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 10:46
 */
public class PayServiceMock implements  PayService {

    @Override
    public CompletableFuture<String> pay() {
        return null;
    }

    @Override
    public String pay2(String name) {
        return "系统异常";
    }
}
