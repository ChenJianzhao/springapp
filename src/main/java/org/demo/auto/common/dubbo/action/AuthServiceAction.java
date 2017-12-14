package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IAuthService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/14.
 */
@Component
public class AuthServiceAction implements IAuthService {

    IAuthService authService;

    public IAuthService getAuthService() {
        return authService;
    }
    @Reference/*(url = "dubbo://localhost:20880")*/
    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }

    public String sayHelloWithAuth(String name) {
        return authService.sayHelloWithAuth(name);
    }

}
