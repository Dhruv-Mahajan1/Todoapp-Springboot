
package com.example.Taskmanagement.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    private static final Logger logger= LoggerFactory.getLogger(LoggerAspect.class);


    @Before("execution(* com.example.Taskmanagement..*(..)) && !execution(* com.example.Taskmanagement.filter..*(..))")
    public void logBefore(JoinPoint jp ) {
        logger.info( jp.getSignature().getName()+" Method called");
    }

    @AfterReturning("execution(* com.example.Taskmanagement..*(..))  && !execution(* com.example.Taskmanagement.filter..*(..))")
    public void logAfter(JoinPoint jp) {
        logger.info(jp.getSignature().getName()+" Method execution completed");

    }

}