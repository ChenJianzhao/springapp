package org.demo.auto.common.redis;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


@Aspect
@Component
public class RedisCacheAspect {
	
    public static final Logger infoLog = Logger.getLogger(RedisCacheAspect.class.getName());

    @Resource
    StringRedisTemplate rt;


    @Pointcut("execution(* org.demo.auto.common.dao.*.select*(..)) " +
            "|| execution(* org.demo.auto.common.dao.*.get*(..)) " +
            "|| execution(* org.demo.auto.common.mapper.*.get*(..))")
    public void cachePointcut() { };
    
    @Pointcut("execution(* org.demo.auto.common.dao.*.delete*(..)) " +
            "|| execution(* org.demo.auto.common.mapper.*.delete*(..))")
    public void evictPointcut() { };
    
    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用;
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中
     * @param jp
     * @return
     * @throws Throwable
     */
//    @Around("execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.select*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.get*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.find*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.search*(..))")
    @Around("cachePointcut()")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {
        // 得到类名、方法名和参数

//        String clazzName = jp.getTarget().getClass().getName();       // MyBatis 下存在一个问题，Mapper 接口没有实现类，target 为代理类，类名可能为 com.sun.proxy.$Proxy29 的形式。
        String clazzName = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        // 根据类名，方法名和参数生成key
        String key = genKey(clazzName, methodName, args);
        if (infoLog.isDebugEnabled()) {
            infoLog.debug("生成key:{}" +  key);
        }

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = me.getAnnotation(RedisCache.class).type();

        // 检查redis中是否有缓存
        String value = (String)rt.opsForHash().get(modelType.getName(), key);

        // result是方法的最终返回结果
        Object result = null;
        if (null == value) {
            // 缓存未命中
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("缓存未命中");
            }

            // 调用数据库查询方法
            result = jp.proceed(args);

            // 序列化查询结果
            String json = serialize(result);

            // 序列化结果放入缓存
            rt.opsForHash().put(modelType.getName(), key, json);
        } else {
            // 缓存命中
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("缓存命中, value = {}" +  value);
            }

            // 得到被代理方法的返回值类型
            Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();

            // 反序列化从缓存中拿到的json
            result = deserialize(value, returnType, modelType);

            if (infoLog.isDebugEnabled()) {
                infoLog.debug("反序列化结果 = {}" +  result);
            }
        }

        return result;
    }

    /**
     * 在方法调用前清除缓存，然后调用业务方法
     * @param jp
     * @return
     * @throws Throwable
     */
//    @Around("execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.insert*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.update*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.delete*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.increase*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.decrease*(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.complaint(..))" +
//            "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.set*(..))")
    @Around("evictPointcut()")
    public Object evictCache(ProceedingJoinPoint jp) throws Throwable {

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = me.getAnnotation(RedisEvict.class).type();

        if (infoLog.isDebugEnabled()) {
            infoLog.debug("清空缓存:{}" + modelType.getName());
        }

        // 清除对应缓存
        rt.delete(modelType.getName());

        return jp.proceed(jp.getArgs());
    }



    /**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    protected String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(Constants.DELIMITER);
        sb.append(methodName);
        sb.append(Constants.DELIMITER);

        for (Object obj : args) {
            sb.append(obj.toString());
            sb.append(Constants.DELIMITER);
        }

        return sb.toString();
    }

    protected String serialize(Object target) {
        return JSON.toJSONString(target);
    }

    protected Object deserialize(String jsonString, Class clazz, Class modelType) {
        // 序列化结果应该是List对象
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(jsonString, modelType);
        }

        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}