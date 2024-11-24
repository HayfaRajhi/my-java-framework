package core.ioc;

public interface IBeanFactory  {
	  Object getBean(String name);
	  void registerBean(String name, Object bean);
	
}
