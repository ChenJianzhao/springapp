package org.demo.auto.common.dubbo.service.stub;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IBarService;

/**
 * Created by cjz on 2017/12/14.
 */
public class BarServiceStub implements IBarService {

    @Reference
    IBarService barService;

    public BarServiceStub(IBarService barService) {
        this.barService = barService;
    }

    public String sayHello(String name) {
        try {
            System.out.println("invoke service by stub");
            String result = barService.sayHello(name);
            return result;
        } catch (Exception e) {
            return "容错数据";
        }
    }
}
