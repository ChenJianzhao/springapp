package org.demo.auto.xml.dubbo;

import org.demo.auto.common.dubbo.service.IDemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
public class DubboConsumer {


    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:auto/xml/dubbo/dubbo-consumer.xml"});

        context.start();

        IDemoService demoService = (IDemoService)context.getBean("demoService"); // 获取 远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法

        System.out.println( hello ); // 显示调用结果
    }
}
