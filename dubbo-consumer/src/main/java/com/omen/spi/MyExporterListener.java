package com.omen.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.ExporterListener;
import org.apache.dubbo.rpc.RpcException;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/28 17:23
 */
public class MyExporterListener  implements ExporterListener {
    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        URL url=exporter.getInvoker().getUrl();
        System.out.println("服务暴露:"+ url.getHost()+":"+url.getPort());
    }

    @Override
    public void unexported(Exporter<?> exporter) {
        System.out.println("服务下线");
    }
}
