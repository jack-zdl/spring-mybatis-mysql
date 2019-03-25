package com.bsg.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhang on 2017/3/29.
 *
 * @description 日志切面编程
 */
/*@Order(2)
@Component
@Aspect*/
public class LogAspect {
//    @Pointcut("execution(public * com.bsg.api.service.*.*(..))")
    public void pointCut() {
    }

    //@Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();

    }
}
