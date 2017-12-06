package org.demo.auto.javaconf.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.demo.auto.common.dao.INewDispUserDao;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewMethodAspect {

    @DeclareParents(value = "")
    public static INewDispUserDao newDispUserDao = null;
}
