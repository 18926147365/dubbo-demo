package com.omen.spi;

import com.omen.constant.SystemConst;
import com.omen.model.RequestModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/22 11:26
 */

public class IDubboLoadBalance extends AbstractLoadBalance {

    Logger logger= LoggerFactory.getLogger(IDubboLoadBalance.class);

    private RoundRobinLoadBalance roundRobinLoadBalance;

    private RoundRobinLoadBalance getRoundRobinLoadBalance(){
        if(roundRobinLoadBalance==null){
            this.roundRobinLoadBalance=new RoundRobinLoadBalance();
        }
        return this.roundRobinLoadBalance;
    }


    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        List<Invoker<T>> newInvokers=new ArrayList<>();
        for(int i=0;i<invokers.size();i++){
            URL iurl= invokers.get(i).getUrl();
            String host=iurl.getHost();
            int port=iurl.getPort();
            String routeToken=iurl.getParameter("routeToken");
            if(StringUtils.isBlank(routeToken)){
                newInvokers.add(invokers.get(i));
            }





        }

        RequestModel rqModel= (RequestModel) RpcContext.getContext().getObjectAttachment(SystemConst.REQUEST_MODEL);
        if(rqModel!=null){
//            System.out.println("ip:"+rqModel.getIp());
//            System.out.println("router_token:"+rqModel.getRouteToken());
        }

        //灰度发布 场景A 上线灰度节点后，让特定带特殊标识符参数的用户走灰度节点


        //灰度发布 场景B 上线灰度节点后，让部分用户进行走灰度节点

        return getRoundRobinLoadBalance().select(invokers,url,invocation);
    }



}
