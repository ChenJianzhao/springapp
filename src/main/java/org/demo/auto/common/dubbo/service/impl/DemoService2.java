package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IDemoService;

//@Service
public class DemoService2 implements IDemoService {
    public String sayHello(String name) {
        return "DemoService2 say Hello to " + name ;
    }
}
