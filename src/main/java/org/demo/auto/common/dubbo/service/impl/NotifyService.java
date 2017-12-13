package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.INotifyService;

/**
 * Created by cjz on 2017/12/13.
 */
@Service
public class NotifyService implements INotifyService{

    public String sayHelloWithNotify(String name) {
        return "hello " + name;
    }
}
