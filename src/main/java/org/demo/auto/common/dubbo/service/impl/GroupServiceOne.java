package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IGourpService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cjz on 2017/12/11.
 */
@Service(group = "one")
public class GroupServiceOne implements IGourpService {
    public int echoNum(int i) {
        return 1;
    }

    public List<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("listItem1");
        return list;
    }

    public String[] getArray() {
        String[] array = new String[1];
        array[0] = "arrayItem1";
        return array;
    }

    public Map<String, String> getMap() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("key1","value1");
        return map;
    }
}
