package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.ICallbackService;
import org.demo.auto.common.dubbo.util.CallbackListener;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */
@Component
public class CallbackServiceAction implements ICallbackService {

    ICallbackService callbackService;

    public ICallbackService getCallbackService() {
        return callbackService;
    }

    @Reference
    public void setCallbackService(ICallbackService callbackService) {
        this.callbackService = callbackService;
    }


    public void addListener(String key, CallbackListener callbackListener) {
        callbackService.addListener(key,callbackListener);
    }
}
