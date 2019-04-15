package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Employer entity class
 *
 */
@Entity
public class Employer {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
    private String name;
    private String description;
    @Embedded
    private Address address;
	
	public Employer() {
	}
	
	/**
	 * the constructor for initializing the data members
	 * @param name
	 * @param description
	 * @param address
	 */
	public Employer(String name, String description, Address address) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
	}

	/**
	 * fetches Id for employer
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * sets employer ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * fetches name of employer
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets name of employer
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * fetches description of employer
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * sets description of an employer
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * fetches address of employer
	 * @return
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * sets address of employer
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
