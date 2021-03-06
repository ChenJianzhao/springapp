package org.demo.auto.javaconf.dubbo;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.registry.Registry;
import org.demo.auto.common.dubbo.service.IDemoService;
import org.demo.auto.common.dubbo.service.IGourpService;
import org.demo.auto.common.dubbo.service.impl.DemoService;
import org.demo.auto.common.dubbo.service.impl.GroupServiceOne;
import org.demo.auto.common.dubbo.service.impl.GroupServiceTwo;
import org.demo.auto.common.dubbo.service.impl.TimeoutDemoService;
import org.demo.auto.javaconf.SubConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * Created by cjz on 2017/12/10.
 */
@Configuration
public class DubboProviderConfig {

    public IDemoService demoService() {
        // 服务实现
        IDemoService demoService = new DemoService();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-of-helloworld-app");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://localhost:2181");
//      registry.setUsername("aaa");
//      registry.setPassword("bbb");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
//      protocol.setThreads(200);

        // 注意:ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置
        ServiceConfig<IDemoService> service = new ServiceConfig<IDemoService>(); // 此实例很重，封装 了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries() service.setProtocol(protocol); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        service.setRef(demoService);
//      service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();

        return demoService;
    }

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
    public ProtocolConfig protocolConfig2(){
        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20881);
        return protocol;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }


    /**
     * 不知道怎么使用 Java 配置配合 Dubbo 注解扫描 Service 类
     * @param application
     * @param registry
     * @param protocolConfig
     * @return
     */
//    @Bean
    public IDemoService demoService(ApplicationConfig application,
                                    RegistryConfig registry,
                                    ProtocolConfig protocolConfig,
                                    ProtocolConfig protocolConfig2,
                                    MonitorConfig monitorConfig) {

        // 服务实现
        IDemoService demoService = new DemoService();

        ServiceConfig<IDemoService> service = new ServiceConfig<IDemoService>(); // 此实例很重，封装 了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries() service.setProtocol(protocol); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        service.setProtocols(Arrays.asList(new ProtocolConfig[]{protocolConfig,protocolConfig2})); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
//        service.setVersion("1.0.0");
        service.setInterface(IDemoService.class);
        service.setMonitor(monitorConfig);

        service.setRef(demoService);

        // 暴露及注册服务
        service.export();

        return service.getRef();
    }

//    @Bean
    public IGourpService groupService(ApplicationConfig application,
                                           RegistryConfig registry,
                                           ProtocolConfig protocolConfig) {

        IGourpService groupServiceOne = new GroupServiceOne();
        ServiceConfig<IGourpService> mergeServiceOne = new ServiceConfig<IGourpService>(); // 此实例很重，封装 了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        mergeServiceOne.setApplication(application);
        mergeServiceOne.setRegistry(registry); // 多个注册中心可以用setRegistries() service.setProtocol(protocol); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        mergeServiceOne.setProtocol(protocolConfig); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        mergeServiceOne.setInterface(IGourpService.class);
        mergeServiceOne.setRef(groupServiceOne);
        mergeServiceOne.setGroup("one");
        mergeServiceOne.export();

        IGourpService groupServiceTwo = new GroupServiceTwo();
        ServiceConfig<IGourpService> mergeServiceTwo = new ServiceConfig<IGourpService>(); // 此实例很重，封装 了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        mergeServiceTwo.setApplication(application);
        mergeServiceTwo.setRegistry(registry); // 多个注册中心可以用setRegistries() service.setProtocol(protocol); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        mergeServiceTwo.setProtocol(protocolConfig); // 多个协议可以用setProtocols() service.setInterface(XxxService.class);
        mergeServiceTwo.setInterface(IGourpService.class);
        mergeServiceTwo.setRef(groupServiceTwo);
        mergeServiceTwo.setGroup("two");
        mergeServiceTwo.export();

        return mergeServiceOne.getRef();
    }

    @Bean
    public IDemoService timeOutDemoService(ApplicationConfig application,
                                    RegistryConfig registry,
                                    ProtocolConfig protocolConfig,
                                    ProtocolConfig protocolConfig2) {


        // 服务实现
        IDemoService demoService = new TimeoutDemoService();

        ServiceConfig<IDemoService> service = new ServiceConfig<IDemoService>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocols(Arrays.asList(new ProtocolConfig[]{protocolConfig,protocolConfig2}));
        service.setInterface(IDemoService.class);
        service.setFilter("activelimit");
        service.setRef(demoService);

        // 暴露及注册服务
        service.export();

//        return service.getRef();
        return null;
    }
}
