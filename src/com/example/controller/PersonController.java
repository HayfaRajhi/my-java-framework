package com.example.controller;

import com.example.service.PersonService;

import core.annotations.orm.Autowired;
import core.annotations.orm.Controller;

//@Controller
public class PersonController {
	private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void managePerson() {
        personService.processPerson();
        System.out.println("Person management completed!");
    }
}
