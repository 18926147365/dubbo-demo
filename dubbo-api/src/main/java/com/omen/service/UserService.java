package com.omen.service;

import com.omen.annotation.IDubboService;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/18 8:46
 */
@IDubboService
public interface UserService {

    String version="1.0.1";

    String getName();
}
