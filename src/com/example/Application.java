package com.example;

import com.example.aspects.TestAspect;

import core.annotations.orm.MyComponentScan;
import core.annotations.orm.MyFrameworkApplicationHR;
import core.ioc.AppFrameworkApplication;
import core.proxy.Command;
import core.proxy.CommandImpl;

@MyFrameworkApplicationHR
@MyComponentScan(basePackages = "com.example")

public class Application {

	public static void main(String[] args) throws Exception {
		//	AppFrameworkApplication.run(CommandImpl.class,TestAspect.class);
		AppFrameworkApplication.run(Application.class,TestAspect.class);
		
	}

	
}




	/*	 
	 * Testing Aspect
	 * Command c =(Command)AppFrameworkApplication.getBean("CommandImpl");
		//c.execute();
		c.execute();
		System.out.println("*************OTHER***********");
		c.other();
		*/
	


