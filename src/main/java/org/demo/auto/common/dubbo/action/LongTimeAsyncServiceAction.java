package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.ILongTimeAsyncService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */
@Component
public class LongTimeAsyncServiceAction implements ILongTimeAsyncService{

    ILongTimeAsyncService longTimeAsyncService;

    public ILongTimeAsyncService getLongTimeAsyncService() {
        return longTimeAsyncService;
    }

    @Reference
    public void setLongTimeAsyncService(ILongTimeAsyncService longTimeAsyncService) {
        this.longTimeAsyncService = longTimeAsyncService;
    }

    public String longTimeAsyncMethod(String name) {
        return longTimeAsyncService.longTimeAsyncMethod(name);
    }

    public String longTimeSyncMehtod(String name) {
        return longTimeAsyncService.longTimeSyncMehtod(name);
    }
}
