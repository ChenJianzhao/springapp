package org.demo.auto.javaconf.rpc;

import javax.annotation.Resource;

import org.demo.auto.common.service.IRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RpcClientConf.class})
public class TestRpcClient {

	@Resource
	IRemoteService remoteService;
	
	@Test
	public void testRpcClient() {
		System.out.println(remoteService.sayHelloTo("cjz"));
	}
	
}
