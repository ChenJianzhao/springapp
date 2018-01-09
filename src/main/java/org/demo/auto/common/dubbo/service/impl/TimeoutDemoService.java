package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IDemoService;

@Service
public class TimeoutDemoService implements IDemoService{

    private volatile  int invokeCount = 0;

    public String sayHello(String name) {

        if( invokeCount++ % 2 == 0) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("DemoService sa Hello to " + name + "time : " + invokeCount);
        return "DemoService sa Hello to " + name ;
    }
}
