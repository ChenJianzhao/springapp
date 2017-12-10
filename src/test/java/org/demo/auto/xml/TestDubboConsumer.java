package org.demo.auto.xml;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.IDemoService;
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
@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-consumer.xml"})
public class TestDubboConsumer {

    @Resource
    IDemoService demoService;

//    @Resource
//    IDemoService demoServiceMock;


    @Test
    public void TestDubboConsumer() throws IOException {

        Assert.assertNotNull(demoService);
        demoService.sayHello("cjz");

    }
}
