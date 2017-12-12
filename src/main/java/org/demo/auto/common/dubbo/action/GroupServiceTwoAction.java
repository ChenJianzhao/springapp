package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by cjz on 2017/12/11.
 */
@Component
public class GroupServiceTwoAction implements IGourpService {

    @Reference(group = "two")
    IGourpService gourpServiceTwo = null;

    public IGourpService getGourpServiceTwo() {
        return gourpServiceTwo;
    }

    public void setGourpServiceTwo(IGourpService gourpServiceTwo) {
        this.gourpServiceTwo = gourpServiceTwo;
    }

    public int echoNum(int i) {
        return gourpServiceTwo.echoNum(i);
    }

    public List<String> getList() {
        return gourpServiceTwo.getList();
    }

    public String[] getArray() {
        return gourpServiceTwo.getArray();
    }

    public Map<String, String> getMap() {
        return gourpServiceTwo.getMap();
    }
}
