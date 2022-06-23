package com.mainul35;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class ExecutionInterceptor {

    private static final Logger LOGGER = Logger.getLogger(ExecutionInterceptor.class);
    @Before("@annotation(Audit)")
    public void intercept(final JoinPoint point) throws Throwable {
        var sig = (MethodSignature) point.getSignature();
        Method method = sig.getMethod();
        method.getDeclaredAnnotation(Audit.class);

        var declaredAnnotationValue = method.getDeclaredAnnotation(Audit.class).role();
        if (declaredAnnotationValue != "ROLE_ADMIN") {
            LOGGER.error(String.format("Invalid role %s on method %s", declaredAnnotationValue, sig.getMethod().getName()));
        }
        LOGGER.info(String.format("Found role %s on method %s", declaredAnnotationValue, sig.getMethod().getName()));
    }

    @Around("execution(public * *(..))")
    public Object intercept(final ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
//        Thread.sleep(2000);
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(String.format("Total execution time = %s", executionTime));
        return point.proceed();
    }
}

