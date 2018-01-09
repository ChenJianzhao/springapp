package org.demo.auto.javaconf.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjz on 2017/12/11.
 */
@Configuration
public class DubboConsumerConfig {

    @Bean
    public ApplicationConfig application() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-of-helloworld-app");
        return application;
    }

    @Bean
    public RegistryConfig registry() {
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://localhost:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");
        return registry;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
//        protocol.setThreads(200);
        return protocol;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

//    @Bean
    public IDemoService demoService(RegistryConfig registry, ApplicationConfig application) {
        // 引用远程服务
        ReferenceConfig<IDemoService> reference = new ReferenceConfig<IDemoService>(); // 此实例很重 ，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏 reference.setApplication(application);
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries() reference.setInterface(XxxService.class);
        reference.setInterface(IDemoService.class);
        reference.setLoadbalance("roundrobin");
//        reference.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        IDemoService demoService = reference.get(); // 注意:此代理对象内部封装了所有通讯细节，对象较重， 请缓存复用
        return demoService;
    }

//    @Bean
    public IGourpService groupService(RegistryConfig registry, ApplicationConfig application) {
        // 引用远程服务
        ReferenceConfig<IGourpService> reference = new ReferenceConfig<IGourpService>(); // 此实例很重 ，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏 reference.setApplication(application);
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries() reference.setInterface(XxxService.class);
        reference.setInterface(IGourpService.class);
        reference.setGroup("*");
        reference.setMerger("true");
        // 和本地bean一样使用xxxService
        IGourpService groupService = reference.get(); // 注意:此代理对象内部封装了所有通讯细节，对象较重， 请缓存复用
        return groupService;
    }

    @Bean
    public IDemoService timeoutDemoService(RegistryConfig registry,
                                           ApplicationConfig application,
                                           MonitorConfig monitorConfig) {

        // 引用远程服务
        ReferenceConfig<IDemoService> reference = new ReferenceConfig<IDemoService>(); // 此实例很重 ，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏 reference.setApplication(application);
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(IDemoService.class);
        reference.setLoadbalance("roundrobin");
        reference.setCluster("failover");
//        reference.setFilter("activelimit");
        reference.setMonitor(monitorConfig);

        // 和本地bean一样使用xxxService
        IDemoService demoService = reference.get(); // 注意:此代理对象内部封装了所有通讯细节，对象较重， 请缓存复用

//        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
//        Registry registry1 = registryFactory.getRegistry(URL.valueOf("zookeeper://localhost:2181"));
//        registry1.register(URL.valueOf("condition://0.0.0.0/org.demo.auto.common.dubbo.service.IDemoService?category=routers&dynamic=true&rule=" + URL.encode(" => port = 20881")));

        return demoService;
    }

}
