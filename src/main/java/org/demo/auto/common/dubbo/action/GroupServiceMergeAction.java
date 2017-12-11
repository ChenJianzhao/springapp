package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/11.
 */
//@Component
public class GroupServiceMergeAction implements IGourpService {

//    @Reference(group = "*")
    IGourpService groupServiceMerge = null;

    public IGourpService getGroupServiceMerge() {
        return groupServiceMerge;
    }

    public void setGroupServiceMerge(IGourpService groupServiceMerge) {
        this.groupServiceMerge = groupServiceMerge;
    }

    public int echoNum(int i) {
        return groupServiceMerge.echoNum(0);
    }
}
