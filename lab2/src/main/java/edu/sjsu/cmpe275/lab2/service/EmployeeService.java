package edu.sjsu.cmpe275.lab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.model.Employee;
import edu.sjsu.cmpe275.lab2.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(empList::add);
		return empList;
	}
	
	public Employee getEmployee(Long id) {
		if(id != null)
			return employeeRepository.findOne(id);
		else
			return null;
	}

	public void addEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	public void updateEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	public void deleteEmployee(long id) {
		employeeRepository.delete(id);
	}

	public void addCollaboration(Employee emp1, Employee emp2) {
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		
	}

}
