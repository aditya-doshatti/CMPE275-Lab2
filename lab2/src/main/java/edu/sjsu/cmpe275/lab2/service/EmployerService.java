package edu.sjsu.cmpe275.lab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.model.Employer;
import edu.sjsu.cmpe275.lab2.repository.EmployerRepository;

/**
 * Employer Service 
 */
@Service
public class EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;

	
	/**
	 * fetches all employers
	 * @return returns employer list
	 */
	public List<Employer> getAllEmployers() {
		List<Employer> empList = new ArrayList<Employer>();
		employerRepository.findAll().forEach(empList::add);
		return empList;
	}
	
	/**
	 * fetches a particular employer
	 * @param id employer ID
	 * @return returns an employer object
	 */
	public Employer getEmployer(long id) {
		return employerRepository.findOne(id);
	}

	/**
	 * adds an employer
	 * @param emp employer object
	 */
	public void addEmployer(Employer emp) {
		employerRepository.save(emp);
	}

	/**
	 * updates employer
	 * @param emp employer object
	 * @param id employer ID
	 */
	public void updateEmployer(Employer emp, long id) {
		employerRepository.save(emp);
	}

	/**
	 * deletes employer
	 * @param id employer ID 
	 */
	public void deleteEmployer(long id) {
		employerRepository.delete(id);
	}

}
