package org.demo.auto.common.dubbo.service;

import org.demo.auto.common.dubbo.util.CallbackListener;

/**
 * Created by cjz on 2017/12/13.
 */
public interface ICallbackService {

    void addListener(String key, CallbackListener callbackListener);
}
