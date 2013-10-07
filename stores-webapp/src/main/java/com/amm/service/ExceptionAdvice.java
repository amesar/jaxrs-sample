package com.amm.stores.service;

//import com.andre.video.util.AppException;
//import com.andre.video.util.RuntimeAppException;
import org.springframework.aop.ThrowsAdvice;
import java.lang.reflect.Method;

public class ExceptionAdvice implements ThrowsAdvice
{
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
		//throws MavenException, Exception
		throws Exception
	{
		debug("method=" + method+" ex="+ex);
		if (ex != null) 
			{
			//if (ex instanceof RuntimeAppException)
				//throw new AppException(ex);
			//if (log.isDebugEnabled())		 
				//ex.printStackTrace(System.err);
			//Exception extarget = converter.map(ex);
			//throw extarget ;
			}
		throw ex;
		//throw new MavenException("require conversion");
	}
	

/*
	@SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation minvoc) throws Throwable
	{
		long t1 = System.currentTimeMillis();
		Object result = minvoc.proceed();
		long time = System.currentTimeMillis()-t1;
		System.out.println(">> ExceptionAdvice.1: time="+time+" Method="+format(minvoc));

		// If the last method parameter is a
/ *
		Class<?>[] parameterTypes = methodInvocation.getMethod().getParameterTypes();
		int lastParameterIndex = parameterTypes.length - 1;
		
		boolean hasTemplateArg = parameterTypes.length > 0
				&& parameterTypes[lastParameterIndex].isAssignableFrom(Template.class) ;

		debug(methodInvocation.getMethod().getName() + " replicate takes " + (System.currentTimeMillis() - lStop));
* /

		return result;
	}

	public String format(MethodInvocation minvoc)
	{
		Method method = minvoc.getMethod();
		return ""
			+ method
			//+ "method="+method.getName()
			;
	}

*/

	void debug(Object o) { System.out.println(">> ExceptionAdvice: "+o);}
}
