package org.demo.auto.xml.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * xml 配置的日志记录切面类
 */
@Component
public class LogAspect {

    public void pointCut() {};
    
    public void logBefore(JoinPoint jp) {
	System.out.println("log before");
    }
    
    public void logAfter(JoinPoint jp) {
	System.out.println("log after");
    }
    
    public void logAround(ProceedingJoinPoint jp) throws Throwable {
	System.out.println("log around before");
	jp.proceed();
	System.out.println("log around after");
    }
    
    public void logAfterReturning() {
	System.out.println("log around after returning");
    }
}
