package org.demo.auto.common.dubbo.service;

/**
 * Created by cjz on 2017/12/12.
 */

import org.demo.auto.common.entity.User;

/**
 * 测试 泛化调用的 Service
 */
public interface IDemoGenericService {

    User consumerHasNoMethod(String name);
}
