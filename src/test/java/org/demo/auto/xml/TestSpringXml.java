package org.demo.auto.xml;

import javax.annotation.Resource;

import org.demo.auto.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cjz on 2017/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/applicationContext.xml"})
public class TestSpringXml {

    @Resource
    User user = null;

    @Test
    public void testXmlConf() {

        System.out.println(user.toString());
        Assert.assertNotNull(user);
    }
}
