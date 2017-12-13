package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IBarService;

/**
 * Created by cjz on 2017/12/14.
 */
@Service
public class BarService implements IBarService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
