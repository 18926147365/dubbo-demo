package com.omen.filter;
import com.alibaba.dubbo.common.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 11:35
 */
@Activate(group = {Constants.PROVIDER} )
public class GlobalTraceFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        //通过其他方法获取机器的环境变量，如端口和机器名称。从而做到链路跟踪
        String traceId = invocation.getAttachment("traceId");
        if(!StringUtils.isBlank(traceId)) {
            RpcContext.getContext().setAttachment("traceId",traceId);
        }else {
            RpcContext.getContext().setAttachment("traceId", UUID.randomUUID().toString());
        }
        System.out.println(traceId);
        return invoker.invoke(invocation);
    }
}
