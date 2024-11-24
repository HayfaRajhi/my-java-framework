package com.example.service;

import com.example.repository.PersonRepository;

import core.annotations.orm.Autowired;
import core.annotations.orm.Service;

@Service
public class PersonService {
	 @Autowired
	    private PersonRepository personRepository;
	 public void processPerson() {
	        personRepository.savePerson();
	        System.out.println("Process completed!");
	    }


}
