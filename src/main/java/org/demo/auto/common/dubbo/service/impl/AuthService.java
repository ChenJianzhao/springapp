package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IAuthService;

/**
 * Created by cjz on 2017/12/14.
 */

/**
 * 通过令牌验证在注册中心控制权限，以决定要不要下发令牌给消费者，可以防止消费者绕过
 * 注册中心访问提供者，另外通过注册中心可灵活改变授权方式，而不需修改或升级提供者
 */
@Service(token = "true")
public class AuthService implements IAuthService {

    public String sayHelloWithAuth(String name) {
        return "say hello with auth to " + name;
    }

}
