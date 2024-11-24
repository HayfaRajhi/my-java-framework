package com.example.model;

import java.util.HashSet;
import java.util.Set;

import core.annotations.orm.Entity;
import core.annotations.orm.OneToMany;
import core.annotations.orm.TableName;
import core.annotations.orm.primaryKey;


@Entity
@TableName(name="person")
public class Person {
	@primaryKey
	private int id;
	private String firstName;
	private String lastName;
	
    //@OneToMany(mappedBy = "author"/*, cascade = CascadeType.ALL*/)
   // private Set<Facture> factures = new HashSet<>();


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
