package edu.sjsu.cmpe275.lab2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.lab2.model.Employee;

/**
 * Interface defining the Employee repository
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	 public List<Employee> findByEmployerId(long id);

}
