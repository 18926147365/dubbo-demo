package com.omen.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:路由配置
 * @Author: lihaoming
 * @Date: 2020/6/22 17:37
 */
@Data
public class RouteConfig implements Serializable {

    private static final long serialVersionUID = -7837121318852296318L;

    private String routeToken;//路由token

    private String routeAddress;//路由地址


}
