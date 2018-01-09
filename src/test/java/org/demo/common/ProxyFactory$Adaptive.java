package org.demo.common;

//package com.alibaba.dubbo.rpc;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.RpcException;

public class ProxyFactory$Adaptive implements com.alibaba.dubbo.rpc.ProxyFactory {
    public com.alibaba.dubbo.rpc.Invoker getInvoker(Object arg0, Class arg1, URL arg2) throws RpcException {

        if (arg2 == null)
            throw new IllegalArgumentException("url == null");
        URL url = arg2;
        String extName = url.getParameter("proxy", "javassist");
        if(extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");

        ProxyFactory extension = (ProxyFactory)ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
        return extension.getInvoker(arg0, arg1, arg2);
    }
    public Object getProxy(Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {
        if (arg0 == null)
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
        if (arg0.getUrl() == null)
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");

        URL url = arg0.getUrl();
        String extName = url.getParameter("proxy", "javassist");
        if(extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");

        ProxyFactory extension = (ProxyFactory)ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
        return extension.getProxy(arg0);
    }
}
