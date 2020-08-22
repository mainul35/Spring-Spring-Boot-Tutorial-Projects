package com.mainul35;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExecutionInterceptor {

//    @Around("@annotation(Audit)")
    @Around("execution(public * *(..))")
    public Object intercept(final ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(point.toString());
        Thread.sleep(2000);
        long executionTime = System.currentTimeMillis() - start;
        return point.proceed();
    }
}

