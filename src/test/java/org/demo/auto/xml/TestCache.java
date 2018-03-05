package org.demo.auto.xml;

import org.demo.auto.common.entity.User;
import org.demo.auto.common.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/applicationContext.xml"})
public class TestCache {

    @Resource
    UserMapper userMapper;

    @Test
    public void testSpringCache() throws InterruptedException {
        Assert.assertNotNull(userMapper);

        User user = userMapper.selectUser(1);
        System.out.println(user);

        Thread.sleep(2000);

        user = userMapper.selectUser(1);
        System.out.println(user);

        user.setUsername("newUsername");
        userMapper.updateUser(user);
        System.out.println(userMapper.selectUser(1));

//        userMapper.deleteUser(1);
//        System.out.println(userMapper.getUserList(Arrays.asList(new Integer[]{1,2})));
    }

}
