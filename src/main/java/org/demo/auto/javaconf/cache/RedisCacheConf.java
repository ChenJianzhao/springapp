package org.demo.auto.javaconf.cache;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring cache 集成 Redis
 * Json 序列化见 http://oppalove.com/2017/03/06/spring-boot-redis-json-examlpe/?i=1
 */
@Configuration
public class RedisCacheConf {

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager =  new RedisCacheManager(redisTemplate);
        /*
            简易兼容方式设置缓存过期时间，通过注解的方式相对复杂，需要自己实现注解
         */
//        Map<String, Long> expires = new HashMap<>();
//        expires.put("User", 3L);
//        cacheManager.setExpires(expires);
        return cacheManager;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }
}
