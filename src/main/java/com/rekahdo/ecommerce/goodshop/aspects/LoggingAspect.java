package com.rekahdo.ecommerce.goodshop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before("com.rekahdo.ecommerce.goodshop.pointcuts.PackagePointcutConfig.rootPackagePointcut()")
	public void logBeforeMethodCall_RootPackage(JoinPoint joinPoint) {
		logger.info("ENTER (-> {} <-)", joinPoint.getSignature());
	}

	@Before("com.rekahdo.ecommerce.goodshop.pointcuts.PackagePointcutConfig.projectPackagePointcut()")
	public void logBeforeMethodCall_ProjectPackage(JoinPoint joinPoint) {
		logger.info("ENTER (-> {} <-)", joinPoint.getSignature());
	}

	@Before("com.rekahdo.ecommerce.goodshop.pointcuts.PackagePointcutConfig.singlePackagePointcut()")
	public void logBeforeMethodCall_SinglePackage(JoinPoint joinPoint) {
		logger.info("ENTER (-> {} <-)", joinPoint.getSignature());
	}

	@Before("com.rekahdo.ecommerce.goodshop.pointcuts.PackagePointcutConfig.doublePackagesPointcut()")
	public void logBeforeMethodCall_DoublePackages(JoinPoint joinPoint) {
		logger.info("ENTER (-> {} <-)", joinPoint.getSignature());
	}
	
}
