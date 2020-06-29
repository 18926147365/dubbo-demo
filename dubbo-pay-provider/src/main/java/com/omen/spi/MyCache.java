package com.omen.spi;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.cache.Cache;
import org.apache.dubbo.cache.filter.CacheFilter;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/29 11:23
 */
@Activate(group = {Constants.PROVIDER})
public class MyCache implements Cache {

    public MyCache(URL url, Invocation invocation) {
        System.out.println(url);
        System.out.println(invocation);
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("put:"+key+":"+value);
    }

    @Override
    public Object get(Object key) {
        System.out.println("get:"+key);
        return null;
    }
}
