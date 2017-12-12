package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IGenericServiceMock;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/13.
 */
@Component
public class GenericServiceMockAction implements IGenericServiceMock {

    @Reference
    IGenericServiceMock genericServiceMock;

    public IGenericServiceMock getGenericServiceMock() {
        return genericServiceMock;
    }

    public void setGenericServiceMock(IGenericServiceMock genericServiceMock) {
        this.genericServiceMock = genericServiceMock;
    }

    public String mockMethod(String name) {
        return genericServiceMock.mockMethod(name);
    }
}
