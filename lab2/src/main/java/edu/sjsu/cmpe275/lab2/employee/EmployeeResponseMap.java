package edu.sjsu.cmpe275.lab2.employee;

import java.util.List;

import edu.sjsu.cmpe275.lab2.address.Address;
import edu.sjsu.cmpe275.lab2.employer.EmployerResponseMap;

public class EmployeeResponseMap {
	private Long id;
    private String name;
    private String email;
    private String title;
    private Address address;
    private EmployerResponseMap employer;
    private ManagerResponseMap manager;
	private List<CollaboratorsMap> collaborators;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public EmployerResponseMap getEmployer() {
		return employer;
	}
	public void setEmployer(EmployerResponseMap employer) {
		this.employer = employer;
	}
	public ManagerResponseMap getManager() {
		return manager;
	}
	public void setManager(ManagerResponseMap tempMang) {
		this.manager = tempMang;
	}
	public List<CollaboratorsMap> getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(List<CollaboratorsMap> collaborators) {
		this.collaborators = collaborators;
	}
}
