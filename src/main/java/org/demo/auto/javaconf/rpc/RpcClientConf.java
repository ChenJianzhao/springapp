package org.demo.auto.javaconf.rpc;

import org.demo.auto.common.service.IRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RpcClientConf {
	
	/**
	 * RmiProxyFactoryBean 是一个工厂bean，该bean可以为 RMI服务创建代理。
	 * @return
	 */
//	@Bean
//	public RmiProxyFactoryBean remoteService() {
//		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
//		rmiProxy.setServiceUrl("rmi://localhost/RemoteService");
//		rmiProxy.setServiceInterface(IRemoteService.class);
//		return rmiProxy;
//	}
	
	@Bean
	public HessianProxyFactoryBean hessianRemoteService() {
		HessianProxyFactoryBean hessianProxy = new HessianProxyFactoryBean();
		hessianProxy.setServiceUrl("http://localhost:8080/springapp/RemoteService.service");
		hessianProxy.setServiceInterface(IRemoteService.class);
		return hessianProxy;
	}
}
