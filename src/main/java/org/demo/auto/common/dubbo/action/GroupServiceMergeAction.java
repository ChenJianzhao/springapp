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
public class GroupServiceMergeAction implements IGourpService {

    /**
     * 分组聚合暂时不知道怎么使用注解， @Reference 注解中没有 merge 属性
     */
    @Reference(group = "*")
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

    public List<String> getList() {
        return groupServiceMerge.getList();
    }

    public String[] getArray() {
        return groupServiceMerge.getArray();
    }

    public Map<String, String> getMap() {
        return groupServiceMerge.getMap();
    }
}
