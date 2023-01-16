package com.ssssv.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@ConditionalOnProperty(name = {"log.aspect.enable"}, havingValue = "true", matchIfMissing = true)
public class LogAspect {

    @Pointcut("execution(* com.ssssv.*.controller.*Controller.*(..)) || execution(* com.ssssv.*.service.*Service.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] reqArgs = proceedingJoinPoint.getArgs();
        String req = new Gson().toJson(reqArgs);
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
        log.info("{}, req: {}", methodName, req);
        Long startTime = System.currentTimeMillis();
        Object respObj = proceedingJoinPoint.proceed();
        String resp = new Gson().toJson(respObj);
        Long endTime = System.currentTimeMillis();
        log.info("{}, response: {}, costTime: {}", methodName, resp, endTime - startTime);
    }
}
