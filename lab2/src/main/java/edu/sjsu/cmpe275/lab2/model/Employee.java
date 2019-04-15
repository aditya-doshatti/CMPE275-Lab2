package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Class for defining the structure of the Employee object 
 */
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMP_ID")
	private long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String title;
    @Embedded
    private Address address;

	@ManyToOne
	@JsonIgnoreProperties(value = {"address", "description"})
    private Employer employer;	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
	@JsonIgnoreProperties(value = {"manager", "reports", "collaborators", "address", "employer", "email", "hibernateLazyInitializer", "handler"})
    private Employee manager;
	
	@OneToMany(mappedBy="manager")
	@JsonIgnoreProperties(value = {"manager", "reports", "collaborators", "address", "employer", "email","hibernateLazyInitializer", "handler"})
    private List<Employee> reports;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name="Collaboration",
	joinColumns={@JoinColumn(name="COLLABORATION_FROM", referencedColumnName="EMP_ID")},
	inverseJoinColumns={@JoinColumn(name="COLLABORATION_TO", referencedColumnName="EMP_ID")})
	@JsonIgnoreProperties(value = {"manager", "reports", "collaborators", "address", "email","hibernateLazyInitializer", "handler"})
    private List<Employee> collaborators;
	
	/**
	 * Constructor for initializing the data members
	 * @param name
	 * @param email
	 * @param title
	 * @param address
	 * @param employer
	 * @param managerId
	 */
	public Employee(String name, String email, String title, Address address, Employer employer, Employee managerId) {
		super();
		this.name = name;
		this.email = email;
		this.title = title;
		this.address = address;
		this.employer = employer;
		if (managerId != null)
			this.setManager(managerId);
	}

	/**
	 * empty constructor
	 */
	public Employee() {
	}

	/**
	 * fetches employee ID
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets employee ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * fetches employee name 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets employee name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * fetches employee's employer
	 * @return
	 */
	public Employer getEmployer() {
		return employer;
	}

	/**
	 * sets employer for employee
	 * @param employer
	 */
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	/**
	 * fetches employee email
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets employee for email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * fetches the manager for employee
	 * @return
	 */
	public Employee getManager() {
		return manager;
	}

	/**
	 * sets the manager for employee
	 */
	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * fetches who the employee is reporting to 
	 * @return
	 */
	public List<Employee> getReports() {
		return reports;
	}

	/**
	 * sets who the employee is reporting to 
	 * @param reports
	 */
	public void setReports(Employee reports) {
		this.reports.add(reports);
	}

	/**
	 * fetches all the collabrators of an employee
	 * @return
	 */
	public List<Employee> getCollaborators() {
		return collaborators;
	}
	
	/**
	 * sets collaborators for an employee
	 * @param collaborators
	 */
	public void setCollaborators(Employee collaborators) {
		if(this.collaborators.contains(collaborators))
			return;
		this.collaborators.add(collaborators);
	}

	/**
	 * fetches title for an employee
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets title fo employee
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * fetches the address of employee
	 * @return
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * sets address of employee
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
