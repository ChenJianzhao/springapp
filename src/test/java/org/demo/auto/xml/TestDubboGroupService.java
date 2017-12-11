package org.demo.auto.xml;

import org.demo.auto.common.dubbo.service.IGourpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by cjz on 2017/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-consumer.xml"})
public class TestDubboGroupService {

    @Resource
    IGourpService groupServiceOneAction = null;

    @Resource
    IGourpService groupServiceTwoAction = null;

//    @Resource
//    IGourpService groupServiceMergeAction = null;

    @Resource
    IGourpService groupServiceMerge = null;

    @Test
    public void TestDubboConsumer() {

//        System.out.println(groupServiceOneAction.echoNum(1));
//        System.out.println(groupServiceTwoAction.echoNum(2));
//        System.out.println(groupServiceMergeAction.echoNum(0));
        System.out.println(groupServiceMerge.echoNum(0));
    }
}
