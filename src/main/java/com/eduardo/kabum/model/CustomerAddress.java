package com.eduardo.kabum.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the customer_address database table.
 * 
 */
@Entity
@Table(name = "customer_address")
@NamedQuery(name = "CustomerAddress.findAll", query = "SELECT c FROM CustomerAddress c")
public class CustomerAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String city;

	private String country;

	private String neighborhood;

	private int number;

	private String state;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "zip_code")
	private int zipCode;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public CustomerAddress() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}