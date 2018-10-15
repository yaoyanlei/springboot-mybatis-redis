package com.neo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.neo.common.RequestContext;

/**
 * @author
 * Created by zhangrui on 16/4/1.
 */
@Aspect
@Component
public class ActionReturnAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestPointcut() {
        // 切点
    }

    @AfterReturning(pointcut="requestPointcut()",returning = "result")
    public void doAfter(JoinPoint jp, Object result) {
        RequestContext.getRequestContext().setResponse(JSONObject.toJSONString(result));
    }


    @Before("requestPointcut()")
    public void doBefore() {
        RequestContext.getRequestContext().setStartTime(System.currentTimeMillis());
    }
}