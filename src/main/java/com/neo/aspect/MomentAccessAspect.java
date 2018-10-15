package com.neo.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.neo.constant.MomentAccessURI;
import com.neo.entity.Moment;

@Aspect
@Component
public class MomentAccessAspect {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(MomentAccessAspect.class);
	
	@Pointcut("@annotation(com.neo.aspect.MomentAccess)")
	public void requestPointcut( ) {
		// 切点
	}
	
	@AfterReturning(pointcut = "@annotation(momentAccess)", returning = "result")
	//@AfterReturning(pointcut = "requestPointcut()", returning = "result")
	public void doAfter(JoinPoint jp,MomentAccess momentAccess, Object result) {
		
		LOGGER.info("要新增的浏览量:"+momentAccess.accessCnt());
		System.err.println("---------------注解的参数---"+momentAccess.accessCnt());
		
		//可 根据不同 URI ，将result解析成不同对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("请求URI:"+request.getRequestURI());
        System.err.println(request.getRequestURI());
        
        if (MomentAccessURI.PET_V1_LIST.equals(request.getRequestURI())) {
        	List<Moment> list = (List<Moment>) result;
    		for (Moment moment : list) {
    			System.err.println(moment);
    		}
		}else if(MomentAccessURI.PET_MOMENT.equals(request.getRequestURI())) {
			Moment moment = (Moment) result;
			System.err.println(moment);
		}
	}
}
