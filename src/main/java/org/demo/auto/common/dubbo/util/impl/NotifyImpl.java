package org.demo.auto.common.dubbo.util.impl;

import org.demo.auto.common.dubbo.util.Notify;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */
@Component
public class NotifyImpl implements Notify {

    public void oninvoke(String arg) {
        System.out.println("onInvoke arg: " + arg);
    }

    public void onreturn(String result, String arg) {
        System.out.println("onReturn result: " + result + " and arg: " + arg);
    }

    public void onthrow(Throwable t, String arg) {
        System.out.println("onThrow t: " + t + " and arg: " + arg);
    }
}
