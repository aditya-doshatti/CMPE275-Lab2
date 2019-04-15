package edu.sjsu.cmpe275.lab2.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.lab2.model.Employer;

/**
 * Interface for defining the employer repository
 */
public interface EmployerRepository extends CrudRepository<Employer, Long> {
	
	 

}
