package org.demo.auto.javaconf.rpc;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RpcServiceConf.class})
public class TestRpcService {

	 @Test
	    public void TestRpcService() throws IOException {
	        System.in.read(); // 按任意键退出
	    }
}
