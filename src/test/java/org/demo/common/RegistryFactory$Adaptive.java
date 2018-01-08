package org.demo.auto.javaconf;

//package com.alibaba.dubbo.registry;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

public class RegistryFactory$Adaptive implements RegistryFactory {

    public Registry getRegistry(URL arg0) {
        if (arg0 == null)
            throw new IllegalArgumentException("url == null");

        URL url = arg0;
        String extName = ( url.getProtocol() == null ? "dubbo" : url.getProtocol() );
        if(extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.registry.RegistryFactory) name from url(" + url.toString() + ") use keys([protocol])");

        RegistryFactory extension = (RegistryFactory)ExtensionLoader.getExtensionLoader(RegistryFactory.class).getExtension(extName);
        return extension.getRegistry(arg0);
    }
}
