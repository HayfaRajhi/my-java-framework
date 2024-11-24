package com.example.model;

import core.annotations.orm.Entity;
import core.annotations.orm.JoinColumn;
import core.annotations.orm.ManyToOne;
import core.annotations.orm.TableName;
import core.annotations.orm.primaryKey;

@Entity
@TableName(name="myproduct")
public class Product {
	@primaryKey
	private int id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Facture getFacture() {
		return facture;
	}

	
	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	





}
