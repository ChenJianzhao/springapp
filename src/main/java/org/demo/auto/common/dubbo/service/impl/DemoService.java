package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/10.
 */
@Service
public class DemoService implements IDemoService {
    public String sayHello(String name) {
        return "Hello " + name ;
    }
}
