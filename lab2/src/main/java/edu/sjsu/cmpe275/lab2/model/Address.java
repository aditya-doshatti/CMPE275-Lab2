package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Embeddable;

/**
 * Class for defining the structure of Address object
 */
@Embeddable
public class Address {

    private String street; // e.g., 100 Main ST
    private String city;
    private String state;
    private String zip;

	/**
	 * Constructor
	 * @param street Street name
	 * @param city City name
	 * @param state State name
	 * @param zip Zip code
	 */
	public Address(String street, String city, String state, String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Returns street name
	 * @return street name
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * sets street name
	 * @param street name
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Returns city name
	 * @return city name
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city name
	 * @param city name
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns name of the state
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * function for setting the state 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * function for fetching ZIP code
	 * @return
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * function for setting the ZIP code
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	public Address() {
		
	}
	
}
