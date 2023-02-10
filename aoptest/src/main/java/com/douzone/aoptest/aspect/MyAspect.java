package com.douzone.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	@Before("execution(public com.douzone.aoptest.vo.ProductVo com.douzone.aoptest.service.ProductService.find(String))")
	public void adviceBefore() {
		System.out.println("--- Before Advice ---");
	}
	
	@After("execution(* *..*.ProductService.find(..))")
	public void adviceAfter() {
		System.out.println("--- After Advice ---");
	}
	
	@AfterReturning("execution(* *..*.ProductService.find(..))")
	public void adviceAfterReturning() {
		System.out.println("--- AfterReturning Advice ---");
	}
	
	@AfterThrowing(value="execution(* *..*.*.*(..))",throwing="ex")
	public void adviceAfterThrowing(Throwable ex) {
		System.out.println("--- adviceAfterThrowing Advice ---"+ex + "ㅡㅡㅡ");
	}
	
	@Around("execution(* *..*.ProductService.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		/* Before */
		System.out.println("--- Around(Before) Advice---");
		
		/* Point Cut Method 실행 */
		Object[] params = {"Calemra"};
		Object result =pjp.proceed(params);
		
		//Object result =pjp.proceed(params);
		/* After Advice */
		System.out.println("--- Around(After) Advice---");
		return result;
	}
}
