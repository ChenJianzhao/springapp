package org.demo.auto.common.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.demo.auto.common.dubbo.service.IBarService;

/**
 * Created by cjz on 2017/12/14.
 */

/**
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，
 * 此时就需要在 API 中带上 Stub，客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给Stub ，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
 *
 * 在 interface 旁边放一个 Stub 实现，它实现 BarService 接口，并有一个传入远程 BarService 实例的构造函数
 * @See org.demo.auto.common.dubbo.service.stub.BarServiceStub
 */
@Service(stub = "org.demo.auto.common.dubbo.service.stub.BarServiceStub",
            mock = "org.demo.auto.common.dubbo.service.mock.BarServiceMock")
public class BarService implements IBarService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
