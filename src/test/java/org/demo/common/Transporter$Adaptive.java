package org.demo.common;

//package com.alibaba.dubbo.remoting;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.remoting.*;

public class Transporter$Adaptive implements Transporter {

    public Client connect(URL arg0, ChannelHandler arg1) throws RemotingException {
        if (arg0 == null)
            throw new IllegalArgumentException("url == null");
        URL url = arg0;
        String extName = url.getParameter("client", url.getParameter("transporter", "netty"));
        if(extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(" + url.toString() + ") use keys([client, transporter])");

        Transporter extension = (com.alibaba.dubbo.remoting.Transporter)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension(extName);
        return extension.connect(arg0, arg1);
    }

    public Server bind(URL arg0, ChannelHandler arg1) throws RemotingException {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        URL url = arg0;
        String extName = url.getParameter("server", url.getParameter("transporter", "netty"));
        if(extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(" + url.toString() + ") use keys([server, transporter])");

        Transporter extension = (Transporter)ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(extName);
        return extension.bind(arg0, arg1);
    }
}
