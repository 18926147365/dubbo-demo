package com.omen.service.stub;

import com.omen.service.PayService;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 10:14
 */
public class PayServiceStub implements PayService {

    private final PayService payService;

    public PayServiceStub(PayService payService) {
        this.payService = payService;
    }

    @Override
    public CompletableFuture<String> pay() {
        return pay();
    }

    @Override
    public String pay2(String name) {

        if(name.equals("hello")){
            return "系统异常";
        }
        try {
            return pay2(name);
        }catch (Exception e){
            return "系统异常";
        }

    }
}
