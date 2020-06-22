package com.omen.spi;


import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

import java.util.List;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/22 10:46
 */
@Activate(group = {Constants.CONSUMER,Constants.PROVIDER} )
public class IDubboLoadBalance extends AbstractLoadBalance {


    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        System.out.println("!@3123");
        return null;
    }
}
