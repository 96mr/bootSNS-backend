package com.spring.boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TimeTraceAop {
	
	@Before("execution(* com.spring.boot.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName());
    }
	
	@Around("execution(* com.spring.boot.service.*.*(..))")
	public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Around before: " + joinPoint.getSignature().getName());
		StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed(); // 조인포인트의 메서드 실행
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

		log.info("Around after: " + joinPoint.getSignature().getName());
		log.info("실행시간 = {}ms", totalTimeMillis);
		return result;
	}
	
	@After("execution(* com.spring.boot.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature().getName());
    }
}
