package com.example.orchestration_serivce.custom_annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAnnotationAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAnnotationAspect.class);

    @Before("@annotation(LogMethodParam)")
    public void logParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("Method {} called with params {}", joinPoint.getSignature(), Arrays.toString(args));
    }
}
