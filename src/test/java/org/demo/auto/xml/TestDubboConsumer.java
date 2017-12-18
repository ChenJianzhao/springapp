package org.demo.auto.xml;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
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
    public void testAuthTfoken() {
        System.out.println(authServiceAction.sayHelloWithAuth("cjz"));
    }

    /**
     * 测试 路由规则
     *
     * 【方法一】启用管理控制台
     *  在 首页 > 服务治理 > 服务 > org.demo.auto.common.dubbo.service.IDemoService > 路由规则 中配置路由规则
     *
     * 【方法二】
     *  使用 api 添加路由规则
     *  NOTE：单元测试时，dynamic 属性设置最好设置成 false，如果设置为 true，要保证客户端不退出
     *  NOTE：需要将 路由规则设置 和 服务调用 分开两个方法调用，如果在设置路由规则后紧接着调用服务，大概率出现路由规则不生效的问题
     *  NOTE：如果只有一个主机提供服务，路由规则将不生效！！！！
     *
     * @throws IOException
     */
    @Test
    public void testAddConditionRoute() throws IOException {

        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("condition://0.0.0.0/org.demo.auto.common.dubbo.service.IDemoService?" +
                "category=routers&" +
                "dynamic=false&" +
                "rule=" + URL.encode("host = 192.168.0.101 => port != 20880") )) ;
    }
    // 测试脚本路由失败
    @Test
    public void testAddScriptRoute() {
        String script =
                "function route(invokers) {\n" +
                "   var result = new java.util.ArrayList(invokers.size());\n" +
                "   for (i = 0; i < invokers.size(); i ++) {\n" +
                "       if (\"20880\".equals(invokers.get(i).getUrl().getPort())) {\n" +
                "           result.add(invokers.get(i));\n" +
                "       }\n" +
                "    }\n" +
                "   return result;\n" +
                "} (invokers);"; // 表示立即执行方法

        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("script://0.0.0.0/org.demo.auto.common.dubbo.service.IDemoService?" +
                "category=routers&" +
                "dynamic=false&" +
                "rule=" + URL.encode(script) )) ;
    }


    @Test
    public void testUseRoute() {
        System.out.println(demoServiceAction.sayHello("route"));
        System.out.println(RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort() );
        System.out.println(demoServiceAction.sayHello("route"));
        System.out.println(RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort() );
//        System.out.println(RpcContext.getContext().getUrl() );
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 测试 动态配置
     *
     */
    @Test
    public void testDynamicConifg() {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("override://0.0.0.0/org.demo.auto.common.dubbo.service.IDemoService?" +
                "category=configurators&" +
                "dynamic=false&" +
                "application=hello-world-app&" +
                "loadbalance=consistenthash"));
    }

    /**
     * 测试 服务降级
     * mock=force:return+null 表示消费方对该服务的方法调用都直接返回 null 值，不发起远 程调用。用来屏蔽不重要服务不可用时对调用方的影响。
     * mock=fail:return+null 表示消费方对该服务的方法调用在失败后，再返回 null 值，不抛异常。用来容忍不重要服务不稳定时对调用方的影响。
     */
    @Test
    public void testDynamicMock() {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
        registry.register(URL.valueOf("override://0.0.0.0/org.demo.auto.common.dubbo.service.IDemoService?" +
                "category=configurators&" +
                "dynamic=false&" +
                "application=hello-world-app&" +
                "mock=force:return+null"));
    }

    @Test
    public void testMonitor() throws InterruptedException {
        while (true) {
            System.out.println(demoServiceAction.sayHello("route"));
            System.out.println(RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort() );
            Thread.sleep(1000);
        }
    }
}

