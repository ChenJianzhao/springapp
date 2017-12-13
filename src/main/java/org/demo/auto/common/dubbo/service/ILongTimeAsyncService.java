package org.demo.auto.common.dubbo.service;

/**
 * Created by cjz on 2017/12/13.
 */
public interface ILongTimeAsyncService {

    String longTimeAsyncMethod(String name);

    String longTimeSyncMehtod(String name);
}
