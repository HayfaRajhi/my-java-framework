package com.example.model;

import core.annotations.orm.Entity;
import core.annotations.orm.JoinColumn;
import core.annotations.orm.ManyToOne;
import core.annotations.orm.TableName;
import core.annotations.orm.primaryKey;


@Entity
public class Facture {
	@primaryKey
	private int id;
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
