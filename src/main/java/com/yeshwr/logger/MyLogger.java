package com.yeshwr.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logger aspect to log 
 * 
 * @author eruvaray
 */
@Aspect
@Component
public class MyLogger {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.yeshwr.*.controller..*.*(..))")
	protected void controller() {
	}

	@Pointcut("execution(* com.yeshwr.*.service..*.*(..))")
	protected void service() {
	}
	
	@Pointcut("execution(* com.yeshwr..*.*(..))")
	protected void allMethod() {
	}
	
	@Before("controller()")
	public void logControllerMethodAccessBefore(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getName();
		String methodName = "Starting Method: " + joinPoint.getSignature().getName();
		log.info("Entered Class: " + className  + " and Invoking Method: " + methodName);
	}

	@After("controller()")
	public void logControllerMethodAccessAfter(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getName();
		String methodName = "Starting Method: " + joinPoint.getSignature().getName();
		log.info("Exited Class: " + className + " and Method: " + methodName);
	}

	@Around("service()")
	public Object logServiceMethodAccessAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getName();
		String methodName = joinPoint.getSignature().getName();
		log.info("Entered Class: " + className + " invoking Method: " + methodName);
		try {
			Object result = joinPoint.proceed();
			log.info("After entering the Class: " + className  + " and invoking the method: " + methodName);
			return result;
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	//Loggers for exceptions in all classes
	
	@AfterThrowing(pointcut = "allMethod()", throwing = "exception")
	public void logExceptions(JoinPoint joinPoint, Throwable exception){
		String className = joinPoint.getSignature().getDeclaringType().getName();
		String methodName = joinPoint.getSignature().getName();
		log.error("An exception has been thrown in Class: " +className +" and Method: "+ methodName);
		log.error("Cause : " + exception.getMessage());
		log.error("Cause : " + exception.getCause());
	}

}
