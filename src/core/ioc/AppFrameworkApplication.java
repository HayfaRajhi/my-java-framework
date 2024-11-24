package core.ioc;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.annotations.orm.Controller;
import core.annotations.orm.Entity;
import core.annotations.orm.JoinColumn;
import core.annotations.orm.ManyToOne;
import core.annotations.orm.MyComponentScan;
import core.annotations.orm.OneToMany;
import core.annotations.orm.Service;
import core.annotations.orm.TableName;
import core.annotations.orm.primaryKey;
import core.jdbc.DataSource;
import core.jdbc.JdbcTemplate;
import core.proxy.Command;
import core.proxy.CommandImpl;
import core.proxy.ProxyFactory;

public class AppFrameworkApplication {
	private static ApplicationContext context;
	private static JdbcTemplate jdbcTemplate;
	private static SQLTableCreator tableCreator;
	private static  DataSource dataSource=new DataSource();
	static {

		initializeFramework();
	}

	private static void initializeFramework() {
		
		context = new ApplicationContext(new BeanFactory());
		jdbcTemplate = new JdbcTemplate(dataSource);
		tableCreator = new SQLTableCreator(new DatabaseTypeMapper(),dataSource);
	}

	public static void run(Class<?> class1, Class<?> class2) throws Exception {

		// Check if the class is annotated with @MyComponentScan
		if (class1.isAnnotationPresent(MyComponentScan.class)) {
			// Retrieve the annotation instance
			MyComponentScan componentScanAnnotation = class1.getAnnotation(MyComponentScan.class);

			// Retrieve the basePackages attribute value
			String basePackages = componentScanAnnotation.basePackages();

			// Scan the specified package and its sub-packages
			List<String> classNames = ComponentScanner.scanClasses(basePackages);
			for (String className : classNames) {
				Class<?> class_ = Class.forName(className);

				if (class_.isAnnotationPresent(Entity.class)) {
					tableCreator.createTableSQL(class_);

				}

			} // end for loop
				// BeanFactory.populateBeans(classNames);
			context.initializeApplication(classNames); // Initializes and manages all beans

			invokeServices();

		}
	}

	private static void invokeServices() {
		// Dynamically invoke services based on the registered beans
		context.getBeansWithAnnotation(Service.class).forEach(bean -> {
			try {
				if (bean.getClass().isAnnotationPresent(Service.class)) {
					Method processMethod = bean.getClass().getMethod("processPerson");
					processMethod.invoke(bean);
					System.out.println("done!");
				}
			} /*
				 * if (bean.getClass().isAnnotationPresent(Controller.class)) { Method
				 * manageMethod = bean.getClass().getMethod("managePerson");
				 * manageMethod.invoke(bean);
				 * System.out.println("Controller action completed!"); } }
				 */
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
