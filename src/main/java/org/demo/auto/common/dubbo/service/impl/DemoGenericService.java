package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IDemoGenericService;
import org.demo.auto.common.entity.User;

/**
 * Created by cjz on 2017/12/12.
 */
@Service
public class DemoGenericService implements IDemoGenericService {

    public User consumerHasNoMethod(String name) {
        String response = "hello " + name;
        System.out.println(response);
        User user = new User();
        user.setUsername(name);
        return user;
    }
}
