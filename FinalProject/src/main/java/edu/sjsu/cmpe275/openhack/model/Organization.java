package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * @author adityadoshatti
 *
 */
@Entity
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String name;
	
	@OneToOne
	private User owner;
	private String description;
	
	@Embedded
	private Address address;
	
	public Organization () {	
	}
	
	public Organization(String name, User owner, String description, Address address) {
		super();
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.address = address;
	}

	public Organization(Organization org) {
		super();
		this.name = org.name;
		this.owner = org.owner;
		this.description = org.description;
		this.address = org.address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
