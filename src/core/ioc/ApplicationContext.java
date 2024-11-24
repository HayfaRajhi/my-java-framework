package core.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import core.annotations.orm.Autowired;
import core.annotations.orm.Controller;
import core.annotations.orm.Repository;
import core.annotations.orm.Service;

public class ApplicationContext {
    private BeanFactory beanFactory;

    public ApplicationContext(BeanFactory beanFactory) {
    	this.beanFactory= beanFactory;
    }

	public void initializeApplication(List<String> classNames) throws Exception {
        populateBeans(classNames);
        injectDependencies();
    }

    private void populateBeans(List<String> classNames) throws Exception {
        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Repository.class) ||
                clazz.isAnnotationPresent(Controller.class)) {
                beanFactory.createBean(clazz);
            }
        }
    }

    private void injectDependencies() throws Exception {
        for (Object bean : beanFactory.getBeanRegistry().values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object dependency = beanFactory.getBean(field.getType().getName());
                    if (dependency == null) {
                        dependency = beanFactory.createBean(field.getType());
                    }
                    field.set(bean, dependency);
                }
            }
        }
    }

    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }
    
    //This method filters the beans managed by the ApplicationContext based 
    //on whether they are annotated with a specific annotation, such as @Service.
    public List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationClass) {
        return beanFactory.getBeanRegistry().values().stream()
                .filter(bean -> bean.getClass().isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }
}
