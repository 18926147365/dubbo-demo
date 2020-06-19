package com.omen.service;


import com.omen.config.ResourceConfig;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/17 18:15
 */

public interface PayService{
     String VERSION ="1.0.0";

     CompletableFuture<String> pay();

     String pay2(String name);


}
