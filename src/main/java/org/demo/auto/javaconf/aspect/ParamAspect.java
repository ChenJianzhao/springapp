package org.demo.auto.javaconf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/6.
 * 为切面类传递方法参数
 */
@Aspect
@Component
public class ParamAspect {
    @Pointcut("execution(* org.demo.auto.common.dao.impl.DispUserDao.display(String)) && args(caller)")
    public void pointCut(String caller) {};

    @Before(value = "pointCut(caller)")
    public void logBefore(JoinPoint jp, String caller) {
        System.out.println(caller + " called before");
    }

    @After(value = "pointCut(caller)")
    public void logAfter(JoinPoint jp, String caller) {
        System.out.println(caller + " called after");
    }

    @Around(value = "pointCut(caller)")
    public void logAround(ProceedingJoinPoint jp, String caller) throws Throwable {
        System.out.println(caller + " called around before");
        jp.proceed();
        System.out.println(caller + " called around after");
    }

    @AfterReturning(value = "pointCut(caller)")
    public void logAfterReturning(String caller) {
        System.out.println(caller + " called around after returning");
    }

}
