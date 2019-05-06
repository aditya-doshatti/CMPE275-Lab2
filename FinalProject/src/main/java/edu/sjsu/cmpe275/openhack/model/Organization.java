package edu.sjsu.cmpe275.openhack.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "ORGANIZATION_ID")
	private Long id;
	@Column(unique=true)
	private String name;
	
	@OneToOne
	private User owner;
	private String description;
	
	@OneToMany
	private List<User> orgUsers;

	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "organization")
	private Set<HackathonSponsorAssoc> hackathons = new HashSet<HackathonSponsorAssoc>();
	
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
	
	public List<User> getOrgUsers() {
		return orgUsers;
	}

	public void setOrgUsers(List<User> orgUsers) {
		this.orgUsers = orgUsers;
	}
	
	public void addOrgUser(User user) {
		this.orgUsers.add(user);
	}

	/**
	 * @return the hackathons
	 */
	public Set<HackathonSponsorAssoc> getHackathons() {
		return hackathons;
	}

	/**
	 * @param hackathons the hackathons to set
	 */
	public void setHackathons(Set<HackathonSponsorAssoc> hackathons) {
		this.hackathons = hackathons;
	}
	
}
