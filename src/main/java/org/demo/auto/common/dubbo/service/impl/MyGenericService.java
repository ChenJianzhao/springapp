package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */

/**
 * 泛化实现，主要用于服务器端没有API接口及模型类元的情况，
 * （没有接口也没有实现？？？？ dubbo 配置文件声明实现的接口，引用一个 mock 实现）
 * 通常用于框架集成，比如:实现一个通用的远程服务 Mock 框架，可通过实现 GenericService 接口处理所有服务请求。
 */
@Component
public class MyGenericService implements GenericService {

    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {

        if( method.equals("mockMethod") ) {
            String response = "mock method say hello to " + args[0];
            System.out.println(response);
            return response;
        }else {

        }
        return null;
    }
}
