package com.example.repository;

import core.annotations.orm.Repository;

@Repository
public class PersonRepository {
	  public void savePerson() {
	        System.out.println("Person saved!");
	    }
}
