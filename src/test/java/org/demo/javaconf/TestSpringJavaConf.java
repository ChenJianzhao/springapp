package org.demo.javaconf;

import org.demo.javaconf.config.JavaConf;
import org.demo.javaconf.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by cjz on 2017/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaConf.class})
public class TestSpringJavaConf {

    @Resource
    User user = null;

    @Test
    public void testJavaConf() {

        System.out.println(user.toString());
        Assert.assertNotNull(user);
    }
}
