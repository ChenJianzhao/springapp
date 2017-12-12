package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.ICacheService;

/**
 * Created by cjz on 2017/12/12.
 */
@Service
public class CacheService implements ICacheService {
    public String cachedSayHello(String name) {
        String response = "Hello " + name;
        System.out.println(response);
        return response;
    }
}
