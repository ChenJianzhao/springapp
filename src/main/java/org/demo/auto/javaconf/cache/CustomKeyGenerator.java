package org.demo.auto.javaconf.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        return target.getClass().getName() + ":" + method.getName() + ":" + new SimpleKey(params);
    }
}
