package com.rekahdo.ecommerce.goodshop.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class PackagePointcutConfig {

	@Pointcut("execution(* com.rekahdo..*(..))")
	public void rootPackagePointcut() {}

	@Pointcut("execution(* com.rekahdo.ecommerce.goodshop.*.*(..))")
	public void projectPackagePointcut() {}

	@Pointcut("execution(* com.rekahdo.ecommerce.goodshop.*.*.*(..))")
	public void singlePackagePointcut() {}

	@Pointcut("execution(* com.rekahdo.ecommerce.goodshop.*.*.*.*(..))")
	public void doublePackagesPointcut() {}
	
}
