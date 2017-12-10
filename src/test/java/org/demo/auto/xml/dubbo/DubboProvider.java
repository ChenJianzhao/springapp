package org.demo.auto.xml.dubbo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
public class DubboProvider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:auto/xml/dubbo/dubbo-provider.xml"});

//        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(new Class[]{});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
