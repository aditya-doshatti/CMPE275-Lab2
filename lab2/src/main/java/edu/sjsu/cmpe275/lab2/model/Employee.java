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

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMP_ID")
	private Long id;
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

	public Employee() {
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
	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getReports() {
		return reports;
	}

	public void setReports(Employee reports) {
		this.reports.add(reports);
	}

	public List<Employee> getCollaborators() {
		return collaborators;
	}
	
	public void setCollaborators(Employee collaborators) {
		if(this.collaborators.contains(collaborators))
			return;
		this.collaborators.add(collaborators);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
