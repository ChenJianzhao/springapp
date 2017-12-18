package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/10.
 */
@Component
public class DemoServiceAction implements IDemoService{

    IDemoService demoService = null;

    public IDemoService getDemoService() {
        return demoService;
    }

    @Reference(loadbalance = "roundrobin")
    public void setDemoService(IDemoService demoService) {
        this.demoService = demoService;
    }

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
