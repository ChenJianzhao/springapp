package org.demo.auto.javaconf.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhCacheConf {


    /**
     * cacheManager() 方法创建了一个 EhCacheCacheManager 的实例，这是通过传入 EhcacheCacheManager 实例实现的。
     * 这是因为 Spring 和 EhCache 都定义了 CacheManager 类型。
     * 需要明确的是，EhCache 的 CacheManager 要被注入到 Spring 的 EhCacheCacheManager  (SpringCacheManager 的实现）之中。
     *
     * @param cm
     * @return
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm) {
        return new EhCacheCacheManager(cm);
    }

    /**
     * 我们需要使用 EhCache 的 CacheManager 来进行注入，所以必须也要声明一个 CacheManager bean。
     * 为了对其进行简化，Spring 提供了 EhCacheManager-FactoryBean 来生成 EhCache 的 CacheManager。
     * 方法 ehcache() 会创建并返回一个 EhCacheManagerFactoryBean 实例。
     * 因为它是一个工厂 bean（也就是说，它实现了 Spring 的 FactoryBean 接口），
     * 所以注册在 Spring 应用上下文中的并不是 EhCacheManagerFactoryBean 的实例，而是 CacheManager 的一个实例，因此适合注入到 EhCacheCacheManager 之中。
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {

        EhCacheManagerFactoryBean ehCacheFactoryBean =  new EhCacheManagerFactoryBean();

        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("auto/xml/cache/ehcache.xml"));

        return ehCacheFactoryBean;
    }

}
