package com.example.aspects;

import core.annotations.aspect.After;
import core.annotations.aspect.Aspect;
import core.annotations.aspect.Before;

@Aspect
public class TestAspect {
	
	@Before("application.CommandImpl.other()")
	public void beforeTreatement() {
		System.out.println("Traitement Before");
	}
	
	@After("application.CommandImpl.other()")
	public void afterTreatment() {
		System.out.println("Traitement After");

		
	}

}
