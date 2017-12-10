package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/10.
 */
@Component
public class DemoServiceMock implements IDemoService{

    IDemoService demoService = null;

    public IDemoService getDemoService() {
        return demoService;
    }

    @Reference
    public void setDemoService(IDemoService demoService) {
        this.demoService = demoService;
    }

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
