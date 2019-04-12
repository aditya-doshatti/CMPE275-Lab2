package edu.sjsu.cmpe275.lab2.employee;

import edu.sjsu.cmpe275.lab2.employer.EmployerResponseMap;

public class CollaboratorsMap {
	private Long id;
    private String name;
    private String title;
    private EmployerResponseMap employer;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public EmployerResponseMap getEmployer() {
		return employer;
	}
	public void setEmployer(EmployerResponseMap employer) {
		this.employer = employer;
	}
}
