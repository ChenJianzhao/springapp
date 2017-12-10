package org.demo.auto.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by cjz on 2017/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/dubbo/dubbo-provider.xml"})
public class TestDubboProvider {

    @Test
    public void TestDubboProvider() throws IOException {

        System.in.read(); // 按任意键退出
    }
}
