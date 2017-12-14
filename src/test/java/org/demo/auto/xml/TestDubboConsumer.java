package org.demo.auto.xml;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.demo.auto.common.dubbo.action.DemoServiceAction;
import org.demo.auto.common.dubbo.action.GenericServiceMockAction;
import org.demo.auto.common.dubbo.service.*;
import org.demo.auto.common.dubbo.service.impl.DemoService;
import org.demo.auto.common.dubbo.util.CallbackListener;
import org.demo.auto.common.dubbo.util.Notify;
import org.demo.auto.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by cjz on 2017/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-consumer.xml"})
public class TestDubboConsumer{

    @Resource
    IDemoService demoServiceAction;

    /**
     * 测试注解普通的服务
     */
    @Test
    public void TestDubboConsumer() {

        Assert.assertNotNull(demoServiceAction);
        String result = demoServiceAction.sayHello("cjz");
        System.out.println(result);
    }


    @Resource
    IGourpService groupServiceOneAction = null;
    @Resource
    IGourpService groupServiceTwoAction = null;
    @Resource
    IGourpService groupServiceMergeAction = null;
    @Resource
    IGourpService groupServiceMerge = null;

    /**
     * 测试分组和聚合
     * @see org.demo.auto.common.dubbo.service.impl.GroupServiceOne
     * @see org.demo.auto.common.dubbo.service.impl.GroupServiceTwo
     * @see org.demo.auto.common.dubbo.action.GroupServiceOneAction
     * @see org.demo.auto.common.dubbo.action.GroupServiceTwoAction
     * @see org.demo.auto.common.dubbo.action.GroupServiceMergeAction
     */
    @Test
    public void TestGroupMerge() {
        // 测试分组1
        System.out.println(groupServiceOneAction.echoNum(1));
        // 测试分组2
        System.out.println(groupServiceTwoAction.echoNum(2));
        // 测试分组聚合
        for (String item : groupServiceMergeAction.getList()) {
            System.out.println(item);
        }

        for(String item : groupServiceMerge.getArray()) {
            System.out.println(item);
        }
    }


    @Resource
    ICacheService cacheServiceAction;

    /**
     * 测试缓存
     * @see org.demo.auto.common.dubbo.action.CacheServiceAction
     */
    @Test
    public void testCachedService() {
        /**
         * comsumer's  result
         * Hello suzy1
         * Hello suzy1
         * Hello suzy2
         * Hello suzy2
         *
         * provider's result
         * Hello suzy1
         * Hello suzy2
         */
        System.out.println(cacheServiceAction.cachedSayHello("suzy1"));
        System.out.println(cacheServiceAction.cachedSayHello("suzy1"));
        System.out.println(cacheServiceAction.cachedSayHello("suzy2"));
        System.out.println(cacheServiceAction.cachedSayHello("suzy2"));
    }


    @Resource
    GenericService demoGenericService;

    /**
     * 测试泛化调用
     * 泛化接口调用方式主要用于客户端没有 API 接口及模型类元的情况，
     * 通常用于框架集成，比如:实现一个通用的服务测试框架，可通过 GenericService 调用所有服务实现。
     * @see org.demo.auto.common.dubbo.service.impl.DemoGenericService
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDemoGenericService() {

        Map<String,String> userMap = (Map<String,String>) demoGenericService.$invoke(
                "consumerHasNoMethod",
                new String[]{"java.lang.String"},
                new Object[]{"fuck the world"});

        User user = new User(userMap.get("username"), null);
        System.out.println(user);
    }


    @Resource
    GenericServiceMockAction genericServiceMockAction;

    /**
     * 测试泛化实现
     * 泛接口实现方式主要用于服务器端没有API接口及模型类元的情况，
     * 通常用于框架集成，比如:实现一个通用的远程服务Mock框架，可通 过实现GenericService接口处理所有服务请求。
     * 服务提供者不需要真的有接口（因为不需要实现），ref 引用一个实现了 GenericService 的 mock，可以处理所有消费端的请求
     * @see org.demo.auto.common.dubbo.service.impl.MyGenericService
     */
    @Test
    public void testGenericServiceMock() {
        System.out.println(genericServiceMockAction.mockMethod("cjz"));
    }

    /**
     * 回声测试
     */
    @Test
    public void testEchoService() {
        String echo = (String)((EchoService)((DemoServiceAction)demoServiceAction).getDemoService()).$echo("echo test");
        System.out.println(echo);
    }

    /**
     * 测试上下文信息和隐式传参
     */
    @Test
    public void testRpcContext() {
        // 远程调用
        demoServiceAction.sayHello("rpcContext");

        // 本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide(); // 获取最后一次调用的提供方IP地址
        if(isConsumerSide) System.out.println("isConsumerSide");

        // 服务端 IP
        String serverIP = RpcContext.getContext().getRemoteHost();
        System.out.println(serverIP);

        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        // 注意:每发起RPC调用，上下文状态会变化
        String service = RpcContext.getContext().getUrl().toServiceString();
        System.out.println(service);
        String interFace = RpcContext.getContext().getUrl().getServiceInterface();
        System.out.println(interFace);

        cacheServiceAction.cachedSayHello("cjz");
        service = RpcContext.getContext().getUrl().toServiceString();
        System.out.println(service);

    }


    @Resource
    ILongTimeAsyncService longTimeAsyncService;

    /**
     * 测试异步调用
     * @throws ExecutionException
     * @throws InterruptedException
     * @see org.demo.auto.common.dubbo.service.impl.LongTimeAsyncService
     * @see auto.xml.dubbo dubbo-consumer.xml <dubbo:reference id="longTimeAsyncService" ... />
     */
    @Test
    public void testAsyncMethodService() throws ExecutionException, InterruptedException {
        // 测试同步方法
        System.out.println("log: sync method start: " + new Date(System.currentTimeMillis()));
        System.out.println(longTimeAsyncService.longTimeSyncMethod("invoke sync method"));
        System.out.println("log: sync method end: " + new Date(System.currentTimeMillis()));

        // 测试异步方法
        System.out.println("log: async method start: " + new Date(System.currentTimeMillis()));
        longTimeAsyncService.longTimeAsyncMethod("invoke async method");
        System.out.println("log: async method end: " + new Date(System.currentTimeMillis()));
        Future<String> barFuture = RpcContext.getContext().getFuture();
        System.out.println(barFuture.get());
        System.out.println("log: async method real finish: " + new Date(System.currentTimeMillis()));
    }


    @Resource
    ICallbackService callbackServiceAction;

    /**
     * 测试参数回调
     * 只需要在 Spring 的配置文件中声明哪个参数是 callback 类型即可。
     * Dubbo 将基于长连接生成反向代理，这样就可以从服务器端调用 客户端逻辑。
     * @see auto.xml.dubbo dubbo-consumer.xml
     * @see org.demo.auto.common.dubbo.service.impl.CallbackService
     */
    @Test
    public void testCallbackService() {
        callbackServiceAction.addListener("Callbackkey", new CallbackListener() {
            public void changed(String msg) {
                System.out.println(msg);
            }
        });
    }


    @Resource
    INotifyService notifyService;
    @Resource
    Notify notifyImpl;

    /**
     * 测试 事件通知
     * 测试 oninvoke,onreturn,onthrow 失败，怀疑和 Spring 版本有关
     */
    @Test
    public void testNotifySeRrvice() {
        Assert.assertNotNull(notifyImpl);
        System.out.println(notifyService.sayHelloWithNotify("suzy"));
    }


    @Resource
    IBarService barServiceAction;

    /**
     * 测试本地存根 service stub 和 本地伪装 service mock
     * @see org.demo.auto.common.dubbo.service.impl.BarService
     * @see org.demo.auto.common.dubbo.service.stub.BarServiceStub
     * @see org.demo.auto.common.dubbo.service.mock.BarServiceMock
     */
    @Test
    public void testBarServiceStubAndMock() {
        System.out.println(barServiceAction.sayHello("stub"));
    }


    @Resource
    IAuthService authServiceAction;

    /**
     * 测试 令牌验证，Service 开启了令牌验证，Reference 直连会抛错误 token 异常
     * @see org.demo.auto.common.dubbo.service.impl.AuthService
     * @see org.demo.auto.common.dubbo.action.AuthServiceAction
     */
    @Test
    public void testAuthToken() {
        System.out.println(authServiceAction.sayHelloWithAuth("cjz"));
    }
}
