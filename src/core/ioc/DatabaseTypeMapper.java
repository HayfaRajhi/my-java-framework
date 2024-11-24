package core.ioc;

public class DatabaseTypeMapper {

	 public String getDatabaseType(Class<?> javaType) {
	        if (javaType == String.class) {
	            return "VARCHAR(255)";
	        } else if (javaType == int.class || javaType == Integer.class) {
	            return "INT";
	        } else {
	            // Add other mappings as needed
	            return "VARCHAR(255)";
	        }
	    }
}
