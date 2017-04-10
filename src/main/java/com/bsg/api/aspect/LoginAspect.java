package com.bsg.api.aspect;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.util.RespJsonFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhang on 2017/3/28.
 */
@Order(1)
@Component("login")
@Aspect
public class LoginAspect {

    @Pointcut("execution(public * com.bsg.api.controller.*.*(..))" +
            "+&&!execution(public * com.bsg.api.controller.LoginController.*(..))")
    public void pointcut(){
    }

    @Before("pointcut()")
    public Object beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();//
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        HttpServletRequest request = null;
//        DictConstants constants = DictConstants.session_user;
        //获得目标的HttpServletRequest
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof HttpServletRequest ){
                request = (HttpServletRequest) list.get(i);
            }
        }

        if (request != null && request.getSession().getAttribute(SysConstants.SESSION_USER) == null){
            return RespJsonFactory.buildSuccess();
        }
        return null;
    }
}
