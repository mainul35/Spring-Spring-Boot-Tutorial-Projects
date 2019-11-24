package com.mainul35;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExecutionInterceptor {


    private static final Logger LOGGER = Logger.getLogger(ExecutionInterceptor.class);

//    @Around("@annotation(Audit)")
    @Around("execution(public * *(..))")
    public Object intercept(final ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(point.toString());
        Thread.sleep(2000);
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(String.format("Executed in %s seconds", executionTime));
        return point.proceed();
    }
}

