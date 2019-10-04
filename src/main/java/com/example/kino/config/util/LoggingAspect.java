//package com.example.kino.config.util;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.jmx.export.annotation.ManagedAttribute;
//import org.springframework.jmx.export.annotation.ManagedResource;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Component
//@Aspect
//@ManagedResource
//public class LoggingAspect {
//    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
//    private static Boolean isEnableLog = true;
//    private static Boolean isArgFunLog = true;
//
//    @ManagedAttribute
//    public Boolean isEnableLog() {
//        return isEnableLog;
//    }
//
//    @ManagedAttribute
//    public void setIsEnableLog(Boolean isEnableLog) {
//        LoggingAspect.isEnableLog = isEnableLog;
//    }
//
//    @ManagedAttribute
//    public Boolean getIsArgFunLog() {
//        return isArgFunLog;
//    }
//
//    @ManagedAttribute
//    public void setIsArgFunLog(Boolean isArgFunLog) {
//        LoggingAspect.isArgFunLog = isArgFunLog;
//    }
//
//    @Around("execution(* com.example.kino.*.*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        Throwable toThrow = null;
//        Object returnValue = null;
//
//        LocalDateTime start = LocalDateTime.now();
//        try {
//            returnValue = joinPoint.proceed();
//        } catch (Throwable t) {
//            toThrow = t;
//        }
//        LocalDateTime stop = LocalDateTime.now();
//
//        if (toThrow == null) {
//            String logs = "\n\n\t\tmethod - " + joinPoint.getSignature();
//            if (isArgFunLog)
//                logs += "\n\t\targuments @ " + Arrays.toString(joinPoint.getArgs()) + "\n\t\treturn    @ " + returnValue + "\n";
//            if (isEnableLog)
//                logs += "\n\t\tstarting  @ " + start.toString() + "\n\t\tfinishing @ " + stop.toString() + "\n\t\tlead time @ " + stop.minusNanos(start.getNano()).getNano() + "\n";
//            logger.info(logs);
//        }
//
//        if (null != toThrow) {
//            logger.error(joinPoint.getSignature() + " : " + toThrow);
//            throw toThrow;
//        }
//
//        return returnValue;
//    }
//}