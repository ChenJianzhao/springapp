package org.demo.auto.javaconf;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.entity.User;
import org.demo.auto.javaconf.aspect.LogAspect;
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

    @Resource
    IDispUserDao dispUserDao = null;
    
    @Resource
    LogAspect logAspect = null;
    
    @Test
    public void testJavaConf() {

        System.out.println(user.toString());
        Assert.assertNotNull(user);
    }
    
    @Test
    public void testAspect() {
	Assert.assertNotNull(dispUserDao);
	Assert.assertNotNull(logAspect);
	dispUserDao.display();
    }
}
