package org.demo.auto.javaconf;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.dao.INewDispUserDao;
import org.demo.auto.common.entity.User;
import org.demo.auto.javaconf.aspect.LogAspect;
import org.demo.auto.javaconf.aspect.NewMethodAspect;
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
    
    @Resource // 切面实现日志记录
    LogAspect logAspect = null;

    @Resource // 使用切面为 bean 引入新功能
    NewMethodAspect newMethodAspect = null;

    @Test
    public void testJavaConf() {

        System.out.println(user.toString());
        Assert.assertNotNull(user);
    }
    
    @Test
    public void testLogAspect() {
        Assert.assertNotNull(dispUserDao);
        Assert.assertNotNull(logAspect);
	    dispUserDao.display("JUnit");
    }

    @Test
    public void testNewMethodAspect() {
        Assert.assertNotNull(newMethodAspect);
        ((INewDispUserDao)dispUserDao).newDisplay();
    }
}
