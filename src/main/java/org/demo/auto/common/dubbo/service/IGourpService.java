package org.demo.auto.common.dubbo.service;

import java.util.List;
import java.util.Map;

/**
 * Created by cjz on 2017/12/11.
 */

/**
 * 测试 服务分组和分组聚合的 Service
 */
public interface IGourpService {

    int echoNum(int i);

    List<String> getList();

    String[] getArray();

    Map<String, String> getMap();
}
