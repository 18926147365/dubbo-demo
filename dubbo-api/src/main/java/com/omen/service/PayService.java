package com.omen.service;


import com.omen.annotation.IDubboService;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */
@IDubboService()
public interface PayService{

    String version ="1.0.11";

     CompletableFuture<String> pay();

     String pay2(String name);



}
