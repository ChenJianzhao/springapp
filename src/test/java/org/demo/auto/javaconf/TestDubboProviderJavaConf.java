package org.demo.auto.javaconf;

import org.demo.auto.javaconf.dubbo.DubboProviderConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DubboProviderConfig.class})
public class TestDubboProviderJavaConf {

    @Test
    public void TestDubboProvider() throws IOException {

        System.in.read(); // 按任意键退出
    }
}
