package org.demo.explicit.javaconf;

import javax.annotation.Resource;

import org.demo.explicit.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
