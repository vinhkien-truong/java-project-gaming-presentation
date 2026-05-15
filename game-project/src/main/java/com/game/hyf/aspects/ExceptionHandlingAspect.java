package com.game.hyf.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j // This annotation from Lombok generates a logger instance named 'log' that we can use to log messages in this class.
@Aspect // This annotation indicates that this class is an Aspect, which allows us to define cross-cutting concerns (like logging) that can be applied across multiple points in the application.
@Component // This annotation makes this class a Spring-managed bean, allowing it to be automatically detected and registered in the application context.
public class ExceptionHandlingAspect {
    /**
        * This aspect will log exceptions thrown by any method in the service or controller layers.
        * It uses @AfterThrowing advice to capture exceptions and log them with the method name and exception message.
        * This helps in debugging and monitoring the application by providing insights into where and why exceptions are occurring.
        * The aspect is applied to all methods within the com.game.hyf.service and com.game.hyf.controller packages, ensuring comprehensive coverage of potential exception sources.
        * The log level used is ERROR, which is appropriate for exceptions.
        * This approach centralizes exception logging, making it easier to maintain and ensuring that all exceptions are consistently logged across the application.
        * Note: This aspect does not handle the exceptions (i.e., it does not modify the flow of the application or return custom responses), it only logs them.
        * By using AOP for exception logging, we can keep our service and controller code clean and focused on business logic, while still ensuring that we have visibility into any errors that occur.
    */
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
