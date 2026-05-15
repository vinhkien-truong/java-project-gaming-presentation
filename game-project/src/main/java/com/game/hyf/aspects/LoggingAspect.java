package com.game.hyf.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    /*
        * This aspect is designed to log method entry, exit, and execution time for all methods within the com.game.hyf.service and com.game.hyf.controller packages.
         * The @Pointcut annotation defines a pointcut that matches all methods in the specified packages.
         * The @Before advice logs the method entry along with the method name and arguments before the method is executed.
         * The @Around advice measures and logs the execution time of the method, as well as the result returned by the method.
         * The @AfterReturning advice logs the method exit along with the method name and the result returned by the method after it completes.
    */
    @Pointcut("within(com.game.hyf.controller..*) || within(com.game.hyf.service..*)")
    public void applicationPackagePointcut() {}

    // Advice to log method entry and exit
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering: {}.{}() with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
    // Advice to log method execution time
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Around: {}.{}() with result = {} (Execution time: {} ms)", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result, (endTime - startTime));
        return result;
    }

    // Advice to log method exit and return value 
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result);
    }

}
