package com.game.hyf.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ExceptionHandlingAspect {
    @AfterThrowing(pointcut = "within(com.game.hyf.service..*)", throwing = "exception")
    public void logServiceExceptions(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.error("Exception in Service " + methodName + ": " + exception.getMessage(), exception);
    }

    @AfterThrowing(pointcut = "within(com.game.hyf.controller..*)", throwing = "exception")
    public void logControllerExceptions(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.error("Exception in Controller " + methodName + ": " + exception.getMessage(), exception);
    }

}
