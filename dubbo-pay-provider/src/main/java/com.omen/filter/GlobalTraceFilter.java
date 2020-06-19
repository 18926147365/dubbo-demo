package com.omen.filter;
import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/19 11:35
 */
@Activate(group = {Constants.PROVIDER} )
public class  ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("asdasdasd");
        return null;
    }
}
