package com.omen.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/22 16:45
 */
@Data
public class RequestModel implements Serializable {
    private static final long serialVersionUID = -7837121315452296318L;

    private String ip;

    private String routeToken;

    private String uid;//从 session中获取的随机字符串

}
