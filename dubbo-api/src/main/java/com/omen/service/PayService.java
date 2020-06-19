package com.omen.service;


import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */

public interface PayService {

     CompletableFuture<String> pay();

     String pay2(String name);


}
