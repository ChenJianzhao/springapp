package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IBarService;

/**
 * Created by cjz on 2017/12/14.
 */

/**
 * @see org.demo.auto.common.dubbo.service.stub.BarServiceStub
 * @see org.demo.auto.common.dubbo.service.mock.BarServiceMock
 */
@Service(stub = "org.demo.auto.common.dubbo.service.stub.BarServiceStub",
        mock = "org.demo.auto.common.dubbo.service.mock.BarServiceMock")
public class BarService implements IBarService {
    public String sayHello(String name) {
        try {
            // 为了测试 mock 休眠 2 秒模拟超时异常，consumer 将捕获异常并调用 mock
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "service say hello to: " + name;
    }
}
