package com.omen.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/29 16:08
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -8559139975231651503L;


    private Integer id;

    private String account;

    private String password;


}
