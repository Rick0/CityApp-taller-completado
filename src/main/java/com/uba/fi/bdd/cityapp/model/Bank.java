package com.uba.fi.bdd.cityapp.model;

public class Bank {

	private Integer id;
	private String name;

	public Bank() {}

	public Bank(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
