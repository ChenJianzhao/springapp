package org.demo.auto.javaconf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* org.demo.auto.common.dao.impl.*.*(..))")
    public void pointCut() {};
    
    @Before(value = "pointCut()")
    public void logBefore(JoinPoint jp) {
	System.out.println("log before");
    }
    
    @After(value = "pointCut()")
    public void logAfter(JoinPoint jp) {
	System.out.println("log after");
    }
    
    @Around(value = "pointCut()")
    public void logAround(ProceedingJoinPoint jp) throws Throwable {
	System.out.println("log around before");
	jp.proceed();
	System.out.println("log around after");
    }
    
    @AfterReturning(value = "pointCut()")
    public void logAfterReturning() {
	System.out.println("log around after returning");
    }
}
