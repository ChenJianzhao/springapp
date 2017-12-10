package org.demo.auto.xml;

import org.demo.auto.common.dubbo.service.IDemoService;
import org.demo.auto.common.dubbo.service.impl.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-consumer.xml"})
public class TestDubboConsumer {

//    @Resource
    IDemoService demoService;

//    @Test
    public void TestDubboConsumer() throws IOException {

        demoService.sayHello("cjz");

    }

    public static void main(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:auto/xml/dubbo/dubbo-consumer.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取 远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法

        System.out.println( hello ); // 显示调用结果
    }
}
