package org.demo.auto.javaconf;

import com.alibaba.dubbo.rpc.RpcContext;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.demo.auto.javaconf.dubbo.DubboConsumerConfig;
import org.demo.auto.javaconf.dubbo.DubboProviderConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DubboConsumerConfig.class})
public class TestDubboConsumerJavaConf {

//    @Resource
    IDemoService demoService;

//    @Resource
    IGourpService groupService;

    @Resource
    IDemoService timeoutDemoService;

//    @Test
    public void TestDubboConsumer() throws IOException {

        Assert.assertNotNull(demoService);
        String result = demoService.sayHello("cjz");
        System.out.println(result);
    }

//    @Test
    public void TestGroupService() {
        // 测试分组聚合
        for (String item : groupService.getList()) {
            System.out.println(item);
        }
    }

    @Test
    public void TestTimeoutDemoService() throws IOException {

        Assert.assertNotNull(timeoutDemoService);
        String result = timeoutDemoService.sayHello("cjz");
        System.out.println(result);
        System.out.println(RpcContext.getContext().getRemotePort());
        result = timeoutDemoService.sayHello("cjz");
        System.out.println(result);
        System.out.println(RpcContext.getContext().getRemotePort());
        result = timeoutDemoService.sayHello("cjz");
        System.out.println(result);
        System.out.println(RpcContext.getContext().getRemotePort());
        result = timeoutDemoService.sayHello("cjz");
        System.out.println(result);
        System.out.println(RpcContext.getContext().getRemotePort());
    }
}
