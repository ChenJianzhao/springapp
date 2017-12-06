package org.demo.auto.javaconf.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.demo.auto.common.dao.INewDispUserDao;
import org.demo.auto.common.dao.impl.NewDispUserDao;
import org.springframework.stereotype.Component;

/**
 * 使用切面为 bean 引入新功能
 */
@Aspect
@Component
public class NewMethodAspect {

    /**
     * value   表示为哪些 bean 添加接口
     * default 指明引入功能的实现类
     * 标注的静态属性指明了要引入的接口
     */
    @DeclareParents(value = "org.demo.auto.common.dao.IDispUserDao+",
            defaultImpl = NewDispUserDao.class)
    public static INewDispUserDao newDispUserDao = null;
}
