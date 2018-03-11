package org.demo.auto.javaconf.rpc;

import java.util.Properties;

import org.demo.auto.common.service.IRemoteService;
import org.demo.auto.common.service.impl.RemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

@Configuration
public class RpcServiceConf {
	
	/**
	 * RMI 方式访问远程服务
	 * 
	 * 配置 RmiServiceExporter，传入实现类 POJO，将生成 RMI 远程服务适配器，
	 * RmiServiceExporter 把 POJO 包装到服务适配器中，
	 * 并将服务适配器绑定到 RMI 注册表中，从而将POJO转换为 RMI 服务
	 * @param remoteService
	 * @return
	 */
//	@Bean
	public RmiServiceExporter exporter(IRemoteService remoteService) {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setService(remoteService);
		exporter.setServiceName("RemoteService");
		exporter.setServiceInterface(IRemoteService.class);
		return exporter;
	}
	
	@Bean
	public HessianServiceExporter hessianExporterRemoteService(IRemoteService remoteService) {
		HessianServiceExporter hessianExporter = new HessianServiceExporter();
		hessianExporter.setService(remoteService);
		hessianExporter.setServiceInterface(IRemoteService.class);
		return hessianExporter;
	}
	
	@Bean
	public HandlerMapping hessianMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.setProperty("/RemoteService.service", "hessianExporterRemoteService");
		mapping.setMappings(mappings);
		return mapping;
	}
	
	@Bean
	public IRemoteService remoteService() {
		return new RemoteService();
	}
}
