package core.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {
	public static Object createProxy(Object target ) throws Exception {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new DynamicInvocationHandler(target));
	}
	 
}
