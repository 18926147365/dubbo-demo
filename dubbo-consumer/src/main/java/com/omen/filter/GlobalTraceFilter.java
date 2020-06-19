package com.omen.filter;
import com.alibaba.dubbo.common.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

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
       
        String traceId = invocation.getAttachment("traceId");
        if(!StringUtils.isBlank(traceId)) {
            RpcContext.getContext().setAttachment("traceId",traceId);
        }else {
            RpcContext.getContext().setAttachment("traceId", UUID.randomUUID().toString());
        }

        return invoker.invoke(invocation);
    }
}
