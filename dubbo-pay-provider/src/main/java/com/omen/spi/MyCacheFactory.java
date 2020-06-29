package com.omen.spi;

import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.cache.Cache;
import org.apache.dubbo.cache.CacheFactory;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/29 11:33
 */
@Activate(group = {Constants.PROVIDER})
public class MyCacheFactory implements CacheFactory {
    @Override
    public Cache getCache(URL url, Invocation invocation) {
        return new MyCache(url,invocation);
    }
}
