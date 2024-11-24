package core.ioc;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import core.annotations.orm.Autowired;

public class BeanFactory {
    private Map<String, Object> beanRegistry = new HashMap<>();

    
    
    public Map<String, Object> getBeanRegistry() {
		return beanRegistry;
	}

	public void setBeanRegistry(Map<String, Object> beanRegistry) {
		this.beanRegistry = beanRegistry;
	}

	public Object getBean(String beanName) {
        return beanRegistry.get(beanName);
    }

    public void registerBean(String beanName, Object beanInstance) {
        beanRegistry.put(beanName, beanInstance);
    }

    public Object createBean(Class<?> clazz) throws Exception {
        Object beanInstance = instantiateBean(clazz);
        registerBean(clazz.getName(), beanInstance);
        return beanInstance;
    }

    private Object instantiateBean(Class<?> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    Object dependency = beanRegistry.get(paramTypes[i].getName());
                    if (dependency == null) {
                        dependency = createBean(paramTypes[i]); // Recursive creation for dependencies
                    }
                    params[i] = dependency;
                }
                return constructor.newInstance(params);
            }
        }
        return clazz.getDeclaredConstructor().newInstance(); // Default constructor if no @Autowired constructor
    }
}
