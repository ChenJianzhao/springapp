package org.demo.auto.xml;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.entity.User;
import org.demo.auto.common.mapper.UserMapper;
import org.demo.auto.xml.aspect.LogAspect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by cjz on 2017/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/applicationContext.xml"})
public class TestSpringXml {

    @Resource
    User user = null;

    @Resource
    IDispUserDao dispUserDao = null;

    @Resource // 切面实现日志记录
    LogAspect logAspect = null;

    @Test
    public void testXmlConf() {

        System.out.println(user.toString());
        Assert.assertNotNull(user);
    }

    @Test
    public void testLogAspect() {
        Assert.assertNotNull(dispUserDao);
        Assert.assertNotNull(logAspect);
        dispUserDao.display("Xml JUnit");
    }


    @Resource
    UserMapper userMapper;

    @Test
    public void testMyBatis(){
        Assert.assertNotNull(userMapper);
//        System.out.println(userMapper.getUser(1));
//        userMapper.deleteUser(1);

        System.out.println(userMapper.getUserList(Arrays.asList(new Integer[]{1,2})));
    }

}
