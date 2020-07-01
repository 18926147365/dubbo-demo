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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //筛选出灰度节点和正常节点
        List<Invoker<T>> newInvokers=new ArrayList<>();//正常节点
        Map<String,List<Invoker<T>>> grayInvokersMap=new HashMap<>();//灰度节点
        for(int i=0;i<invokers.size();i++){
            URL iurl= invokers.get(i).getUrl();
            //只有灰度节点才有routeToken,生产节点为空
            String routeToken=iurl.getParameter("routeToken");
            if(StringUtils.isBlank(routeToken)){
                newInvokers.add(invokers.get(i));
            }else{
                List<Invoker<T>> grayList=grayInvokersMap.get(routeToken);
                if(grayList==null || grayList.size()==0){
                    grayList=new ArrayList<>();
                }
                grayList.add(invokers.get(i));
                grayInvokersMap.put(routeToken,grayList);
            }
        }


        RequestModel rqModel= (RequestModel) RpcContext.getContext().getObjectAttachment(SystemConst.REQUEST_MODEL);
        if(rqModel!=null && StringUtils.isNotBlank(rqModel.getRouteToken())){
            //走灰度节点
            List<Invoker<T>> list= grayInvokersMap.get(rqModel.getRouteToken());
            //若灰度节点未找到，则还是走回正常节点
            if(list!=null && list.size()!=0){
                return getRoundRobinLoadBalance().select(list,url,invocation);
            }
        }
        return getRoundRobinLoadBalance().select(newInvokers,url,invocation);

    }



}
