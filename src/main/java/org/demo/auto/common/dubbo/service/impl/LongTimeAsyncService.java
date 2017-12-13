package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.ILongTimeAsyncService;

/**
 * Created by cjz on 2017/12/13.
 */
@Service(timeout = 5000)
public class LongTimeAsyncService implements ILongTimeAsyncService {
    public String longTimeAsyncMethod(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String longTimeSyncMehtod(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }
}
