package core.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.example.aspects.TestAspect;

import core.annotations.aspect.After;
import core.annotations.aspect.Before;
import core.annotations.orm.Autowired;

public class DynamicInvocationHandler implements InvocationHandler {

	private Object target;
	private TestAspect aspect;
    private Map<String, Object> cache = new HashMap<>();

	public DynamicInvocationHandler(Object target) throws Exception {
		this.target = target;
		this.aspect = new TestAspect();
		
	  injectDependencies(target);

	}
	   private void injectDependencies(Object target) throws IllegalAccessException, Exception {
	        Field[] fields = (Field[]) target.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            if (field.isAnnotationPresent(Autowired.class)) {
	                Object dependency = createDependencyInstance(field.getType());
	                field.setAccessible(true);
	                field.set(target, fields);
	            }
	        }
	    }
	   

	    private Object createDependencyInstance(Class<?> dependencyClass) throws IllegalAccessException, InstantiationException {
	        // Create and return an instance of the dependency class
	        return dependencyClass.newInstance();
	    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> class2 = Class.forName("com.example.aspects.TestAspect"); //normalementrun ta3ml instance kital9a aliha @aspect w tab3athhelk
		Method[] methods = class2.getDeclaredMethods();
		Object result = null;

		String methodName = "";
		boolean beforeExecuted = false;
		for (Method meth : methods) {
			Before beforeAnnotation = meth.getAnnotation(Before.class);
			After afterAnnotation = meth.getAnnotation(After.class);
			if (beforeAnnotation != null) {
				String value = beforeAnnotation.value();
				methodName = extractMethodName(value);

				if (methodName.equals(method.getName()) && !beforeExecuted) {
					aspect.beforeTreatement();
					result = method.invoke(target, args);

					beforeExecuted = true;

				} else {
					beforeExecuted = true;
					result = method.invoke(target, args);

				}

			}

			if (afterAnnotation != null) {

				String value = afterAnnotation.value();
				methodName = extractMethodName(value);

				if (methodName.equals(method.getName())) {
					aspect.afterTreatment();

				}
			}
		}

		return result;
	}

	private static String extractMethodName(String value) {
		Pattern pattern = Pattern.compile("\\.([a-zA-Z]+)\\(\\)");
		Matcher matcher = pattern.matcher(value);

		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}
}
