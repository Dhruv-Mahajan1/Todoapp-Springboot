package com.example.Taskmanagement.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasureTimeImp {

    @Around("@annotation(com.dhruv.ToDoApp.annotation.MeasureTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {


        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("\n"+joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
