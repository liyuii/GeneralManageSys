package com.web.aspect;

import com.web.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.web.annotation.LogAnnotation)")
    public void pointCut(){}

//    @Before(value = "pointCut() && @annotation(logAnnotation)")
    @Before("pointCut()")
    public void start(){
        System.out.println("Before...");
//        Object[] args = joinPoint.getArgs();
//        System.out.print(joinPoint.getSignature().getName()+"运行，参数列表："+ Arrays.asList(args));
//        System.out.println("；注解为："+logAnnotation);
    }
}
