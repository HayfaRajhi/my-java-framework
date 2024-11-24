package core.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.annotations.orm.Autowired;
import core.annotations.orm.Controller;
import core.annotations.orm.Repository;
import core.annotations.orm.Service;

public class MyInjectinator {
/*	private Map<Class<?>, Object> singletonBeans = new HashMap<>();
	private Map<Class<?>, Object> beanRegistry = new HashMap<>();

	public void scanAndRegisterBeans(List<String> classNames) {
		for (String className : classNames) {
			try {
				Class<?> ourClass = Class.forName(className);
				if (ourClass.isAnnotationPresent(Service.class) || ourClass.isAnnotationPresent(Repository.class)
						|| ourClass.isAnnotationPresent(Controller.class)) {
					// Create an instance of the class
					Object beanInstance = ourClass.getDeclaredConstructor().newInstance();
					// Check for @Autowired annotations and perform dependency injection
					injectDependencies(beanInstance);

					// Add the bean instance to the registry
					// beanRegistry.put(ourClass.getName(), beanInstance);
					beanRegistry.put(ourClass, beanInstance);

				}
			} catch (Exception e) {
				// Handle exceptions (e.g., ClassNotFoundException, InstantiationException)
				e.printStackTrace();
			}
		}
	}

	private void injectDependencies(Object beanInstance) throws Exception {

		for (Field field : beanInstance.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Autowired.class)) {
				field.setAccessible(true);
				// Retrieve the bean from the registry based on the field's type
				Object dependency = beanRegistry.get(field.getType());
				if (dependency != null) {
					field.set(beanInstance, dependency); // Inject the dependency
				} else {
					throw new RuntimeException("Dependency not found in bean registry: " + field.getType());
				}
			}
		}
		// Check if the class has a constructor annotated with @Autowired
		Constructor<?>[] constructors = beanInstance.getClass().getConstructors();
		// Check if the class has a constructor annotated with @Autowired
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(Autowired.class)) {
				// Get the parameter types of the constructor
				Class<?>[] parameterTypes = constructor.getParameterTypes();
				Object[] constructorArgs = new Object[parameterTypes.length];
				int i = 0;

				// Iterate through the parameter types to resolve dependencies
				for (Class<?> parameterType : parameterTypes) {
					// Retrieve the corresponding dependency from the beanRegistry
					Object dependency = beanRegistry.get(parameterType);
					if (dependency != null) {
						constructorArgs[i++] = dependency;
					} else {
						// Handle the case where the required dependency is not found
						throw new RuntimeException("Dependency not found in bean registry: " + parameterType);
					}
				}

				// Invoke the constructor with resolved dependencies
				beanInstance = constructor.newInstance(constructorArgs);
			}
			// Dynamically invoke services based on the registered beans
		}

		// Check for @Autowired on setter methods and inject dependencies
		for (Method method : beanInstance.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(Autowired.class) && method.getName().startsWith("set")) {
				// Get the parameter type of the setter method
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length == 1) {
					// Retrieve the corresponding dependency from the beanRegistry
					Object dependency = beanRegistry.get(parameterTypes[0]);
					if (dependency != null) {
						// Invoke the setter method to inject the dependency
						method.invoke(beanInstance, dependency);
					} else {
						// Handle the case where the required dependency is not found
						throw new RuntimeException("Dependency not found in bean registry: " + parameterTypes[0]);
					}
				}
			}
		}
	}*/

}
