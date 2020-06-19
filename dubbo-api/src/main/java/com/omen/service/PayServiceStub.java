package com.omen.service;

import org.apache.commons.lang.StringUtils;

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
        return payService.pay();
    }

    @Override
    public String pay2(String name) {
       if(StringUtils.isBlank(name)){
           return "空名称";
       }
       try {
          return payService.pay2(name);
       }catch (Exception e){
           e.printStackTrace();
           System.out.println("系统繁忙");
           return "系统繁忙";
       }

    }
}
