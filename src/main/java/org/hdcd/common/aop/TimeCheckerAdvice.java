package org.hdcd.common.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hdcd.common.domain.PerfomanceLog;
import org.hdcd.common.service.PerformanceLogService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class TimeCheckerAdvice {
    private final PerformanceLogService performanceLogService;

    @Around("execution(* org.hdcd.service.*Service*.*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        log.info(Arrays.toString(pjp.getArgs()));

        Signature signature = pjp.getSignature();
        Object target = pjp.getTarget();

        String signatureName = signature.getName();
        String signatureDeclaringTypeName = signature.getDeclaringTypeName();

        log.info("signature.getName() = "+signatureName);
        log.info("signature.getDeclaring = "+signature.getDeclaringTypeName());
        log.info("target : "+target);

        Object result = pjp.proceed();

        long endTime= System.currentTimeMillis();

        long durationTime = endTime - startTime;

        PerfomanceLog perfomanceLog = new PerfomanceLog();
        perfomanceLog.setSignatureName(signatureName);
        perfomanceLog.setSignatureTypeName(signatureDeclaringTypeName);
        perfomanceLog.setDurationTime(durationTime);


        performanceLogService.register(perfomanceLog);
        return  result;

    }
}
