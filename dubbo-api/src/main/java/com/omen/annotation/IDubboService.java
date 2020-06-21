package com.omen.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/20 10:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface IDubboService {

    String name() default "" ;//默认不填则直接使用'类名'+'.version' 如UserService.version 对应xml配置的服务name
}
