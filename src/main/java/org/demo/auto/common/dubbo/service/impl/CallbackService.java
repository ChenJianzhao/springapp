package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.ICallbackService;
import org.demo.auto.common.dubbo.util.CallbackListener;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */
//@Component
//@Service
public class CallbackService implements ICallbackService {
    public void addListener(String key, CallbackListener callbackListener) {
        callbackListener.changed("receive msg: " + key);
    }
}
