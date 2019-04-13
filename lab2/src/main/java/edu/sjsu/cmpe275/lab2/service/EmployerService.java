package edu.sjsu.cmpe275.lab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.model.Employer;
import edu.sjsu.cmpe275.lab2.repository.EmployerRepository;

@Service
public class EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;

	
	public List<Employer> getAllEmployers() {
		List<Employer> empList = new ArrayList<Employer>();
		employerRepository.findAll().forEach(empList::add);
		return empList;
	}
	
	public Employer getEmployer(long id) {
		return employerRepository.findOne(id);
	}

	public void addEmployer(Employer emp) {
		employerRepository.save(emp);
	}

	public void updateEmployer(Employer emp, long id) {
		employerRepository.save(emp);
	}

	public void deleteEmployer(long id) {
		employerRepository.delete(id);
	}

}
