package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 1.定义切面类
 */
@Aspect
@Component
public class AspectTest {

    //2.定义切点，仅对AopTest注解的方法做切面
    @Pointcut("@annotation(com.example.demo.aop.annotation.AopTest)")
    public void pointCut(){
    }

    //3.创建一个环形通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        System.out.println("操作A");

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("操作B");
        return obj;
    }



}
