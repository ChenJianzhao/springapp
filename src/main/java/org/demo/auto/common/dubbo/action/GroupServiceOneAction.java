package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/11.
 */
@Component
public class GroupServiceOneAction implements IGourpService{

    @Reference(group = "one")
    IGourpService gourpServiceOne = null;

    public IGourpService getGourpServiceOne() {
        return gourpServiceOne;
    }

    public void setGourpServiceOne(IGourpService gourpServiceOne) {
        this.gourpServiceOne = gourpServiceOne;
    }

    public int echoNum(int i) {
        return gourpServiceOne.echoNum(i);
    }
}
