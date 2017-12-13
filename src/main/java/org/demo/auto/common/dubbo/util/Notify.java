package org.demo.auto.common.dubbo.util;

/**
 * Created by cjz on 2017/12/13.
 */
public interface Notify {

    void oninvoke(String arg);

    void onreturn(String result, String arg);

    void onthrow(Throwable t, String arg);
}
