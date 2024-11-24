package core.ioc;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import core.annotations.orm.Entity;


public class ComponentScanner {
private  static List<String> scannedClasses;

	 public static List<String> scanClasses(String basePackage) {
	        
		 
		    List<String> classNames = new ArrayList<>();
	        String basePath = basePackage.replace('.', '/'); // Convert package name to directory path

	        // Get the base directory for scanning
	        File baseDirectory = new File(AppFrameworkApplication.class.getResource("/" + basePath).getFile());
	        scanClass(baseDirectory, basePackage, classNames);
	        scannedClasses=classNames;
	        return scannedClasses;
	    }
	 


	 private static void scanClass(File directory, String packageName, List<String> classNames) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                	scanClass(file, packageName + "." + file.getName(), classNames); // Recursively scan sub-directories
	                } else if (file.getName().endsWith(".class")) {
	                    String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
	                    classNames.add(className);
	                    System.out.println("Scanned class: " + className); // Display scanned class in console
	                }
	            }
	        }
	    }
	    
	 
	 /*	public static List<String> getEntityClasses() throws ClassNotFoundException{
     List<String> classNames = new ArrayList<>();

	
		 for (String className : scannedClasses) {
			Class<?> clazz = Class.forName(className);

			if (clazz.isAnnotationPresent(Entity.class)) {
				classNames.add(className);
			}
			
	}
	
		 return classNames;
	}
	*/
	    







}
