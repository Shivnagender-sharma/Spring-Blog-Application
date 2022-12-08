package com.deloite.blog.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Before(value = "execution(* com.deloite.blog.controllers.*.*(..)) and args(object)")
//	public void beforeAdvice (JoinPoint joinPoint, Object object) {
//		logger.info("Request="+ object);
//	}
	
	
//	@After(value = "execution(* com.deloite.blog.controllers.*.*(..)) and args(object)")
//	public void beforeAdvice (JoinPoint joinPoint, Object object) {
//		logger.info("Request="+ object);
//	}
	
	@AfterReturning(value = "execution(* com.deloite.blog.controllers.*.*(..)) and args(object)",
			     returning = "returningObject" )
	public void AfterAdvice (JoinPoint joinPoint, Object object , Object returningObject) {
		logger.info("Response="+ returningObject);
	}
	
//	@Around(value = "execution(* com.deloite.blog.controllers.*.*(..)) and args(object)")
//    public void aroundAdvice (ProceedingJoinPoint proceedingJoinPoint, Object object) {
//	  logger.info("Request="+ object);
//	
//	Object returningObject = null;
//	try {
//		returningObject = proceedingJoinPoint.proceed();
//	} catch (Throwable e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//	logger.info("Response = "+ returningObject);
//  }
//	
	

}
