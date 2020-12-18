package com.cbash.cardatabase.domain;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;

@javax.persistence.Entity
@com.fasterxml.jackson.annotation.JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	private long ownerid;
	private String firstname, lastname;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.persistence.OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private java.util.List<Car> cars;

	public Owner() {
	}

	public Owner(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
