package org.demo.auto.common.dubbo.service.mock;

import org.demo.auto.common.dubbo.service.IBarService;

/**
 * Created by pc on 2017/12/14.
 */

/**
 * 1. Mock 是 Stub 的一个子集，便于服务提供方在客户端执行容错逻辑，因经常需要在出 现 RpcException (比如网络失败，超时等)时进行容错，
 * 而在出现业务异常(比如登录用户 名密码错误)时不需要容错，如果用 Stub，可能就需要捕获并依赖 RpcException 类，
 * 而 用 Mock 就可以不依赖 RpcException，因为它的约定就是只有出现 RpcException 时才 执行。
 *
 * 2. 在 interface 旁放一个 Mock 实现，它实现 BarService 接口，并有一个无参构造函数
 */
public class BarServiceMock implements IBarService{

    public String sayHello(String name) {
        System.out.println("catch some RpcException when invoke service (like Timeout Exception)");
        System.out.println("log: return value by mock");
        return "mock say hello to :" + name;
    }
}
