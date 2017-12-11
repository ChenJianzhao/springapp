package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IGourpService;

/**
 * Created by cjz on 2017/12/11.
 */
@Service(group = "one")
public class GroupServiceOne implements IGourpService {
    public int echoNum(int i) {
        return 1;
    }
}
