package org.demo.auto.xml;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.demo.auto.common.dubbo.action.GenericServiceMockAction;
import org.demo.auto.common.dubbo.service.ICacheService;
import org.demo.auto.common.dubbo.service.IDemoGenericService;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.demo.auto.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cjz on 2017/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-consumer.xml"})
public class TestDubboConsumer {

    @Resource
    IDemoService demoServiceAction;

    @Resource
    ICacheService cacheServiceAction;

    @Resource
    GenericService demoGenericService;

    @Test //测试注解普通的服务
    public void TestDubboConsumer() throws IOException {

        Assert.assertNotNull(demoServiceAction);
        String result = demoServiceAction.sayHello("cjz");
        System.out.println(result);
    }


    @Test   // 测试缓存
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

    @Test   // 测试泛化引用
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

    @Test
    public void testGenericServiceMock() {
        System.out.println(genericServiceMockAction.mockMethod("cjz"));
    }
}
