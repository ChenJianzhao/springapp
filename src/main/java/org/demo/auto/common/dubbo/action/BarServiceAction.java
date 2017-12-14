package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IBarService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by pc on 2017/12/14.
 */
@Component
public class BarServiceAction implements IBarService {

    IBarService barService;

    public IBarService getBarService() {
        return barService;
    }

    @Reference(mock = "org.demo.auto.common.dubbo.service.mock.BarServiceMock")
    public void setBarService(IBarService barService) {
        this.barService = barService;
    }

    public String sayHello(String name) {
        return barService.sayHello(name);
    }
}
