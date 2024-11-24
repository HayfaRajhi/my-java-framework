package core.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataSource {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	
	private Map<String ,String> properties=new HashMap<String,String>();
	
	public DataSource() {
		String line =null;
		Scanner sc  = null;
		try {
			sc=new Scanner(new File ("/home/hayfa/eclipse-workspace-framework-build/design-pattern-main/my_Framework/ressources/application.properties"));
			while (sc.hasNextLine()) {
				line=sc.nextLine();
				String[]parts =line.split("=",2);
				if(parts.length>=2) {
					String key =parts[0];
					String value =parts[1];
					properties.put(key, value);
					
				}
				else {
					System.out.println("ignoring line: "+line);
				}
			}
			sc.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		setProperties();
		
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	private void setProperties() {  //page 19 p5
		driverClassName=properties.get("myorm.datasource.driver-class-name");
		url=properties.get("myorm.datasource.url");
		username=properties.get("myorm.datasource.username");
		password=properties.get("myorm.datasource.password");
	}
	

}
