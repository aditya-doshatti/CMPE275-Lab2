package edu.sjsu.cmpe275.lab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.model.Employee;
import edu.sjsu.cmpe275.lab2.repository.EmployeeRepository;

/**
 * Service class for employee repository functions
 * @return
 */
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	/**
	 * fetches all employees
	 * @return employee list
	 */
	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(empList::add);
		return empList;
	}

	/**
	 * fetches a particular employee
	 * @param id accepts employee ID
	 * @return employee information
	 */
	public Employee getEmployee(Long id) {
		if(id != null)
			return employeeRepository.findOne(id);
		else
			return null;
	}

	/**
	 * adds employee to employee repository
	 * @param emp employee object
	 */
	public void addEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	/**
	 * updates employee information
	 * @param emp employee object
	 */
	public void updateEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	/**
	 * deletes employee from repository
	 * @param id employee ID
	 */
	public void deleteEmployee(long id) {
		employeeRepository.delete(id);
	}

	/**
	 * adds collaboration between employees
	 * @param emp1 first employee object
	 * @param emp2 second employee object
	 */
	public void addCollaboration(Employee emp1, Employee emp2) {
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		
	}

}
