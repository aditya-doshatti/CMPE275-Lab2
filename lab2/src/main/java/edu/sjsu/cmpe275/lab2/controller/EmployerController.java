package edu.sjsu.cmpe275.lab2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Employee;
import edu.sjsu.cmpe275.lab2.model.Employer;
import edu.sjsu.cmpe275.lab2.service.EmployeeService;
import edu.sjsu.cmpe275.lab2.service.EmployerService;

/**
 * Class  to handle the API  calls associated with employer 
 */
@RestController
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	/**
	 * function to handle API call for fetching information of all employees
	 * @return relevant response code is returned
	 */
	@RequestMapping(value = "/employers", produces = { "application/json", "application/xml" })
	public List<Employer> getAllEmployers() {
		return employerService.getAllEmployers();
	}
	
	/**
	 * This function handles the API call for fetching information a particular employer
	 * @param id function accepts the employer id as its sole parameter
	 * @return relevant response code is returned along with requested information
	 */
	@RequestMapping(value = "/employers/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Employer> getEmployer(@PathVariable long id) {
		Employer emp =  employerService.getEmployer(id);
		if(emp !=null)
			return ResponseEntity.ok(emp);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);		
	}
	
	/**
	 * This function handles the API call to add an employer 
	 * function takes below parameters for creating the employer object
	 * @param name employer name
	 * @param description employer description
	 * @param street employer street
	 * @param city employer city
	 * @param state employer state
	 * @param zip employer zip
	 * @return appropriate response code is returned 
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/employers", produces = { "application/json", "application/xml" })
	public ResponseEntity<Employer> addEmployer(@RequestParam String name
            , @RequestParam(required = false) String description
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip) {
		Employer emp = new Employer();
		emp.setName(name);
		emp.setDescription(description);
		emp.setAddress(new Address(street, city, state, zip));
		employerService.addEmployer(emp);
		return ResponseEntity.ok(emp);
	}
	
	/**
	 * This function handles the API call to update employer information 
	 * @param id employer id
	 * @param name employer name
	 * @param description employer description
	 * @param street employer street
	 * @param city employer city
	 * @param state employer state
	 * @param zip employer zip
	 * @return appropriate response code is returned depending on success or failure
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.PUT, value="/employers/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> updateEmployer(@PathVariable long id, @RequestParam String name
            , @RequestParam(required = false) String description
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip) {
		Employer emp =  employerService.getEmployer(id);
		if(emp ==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		if(name != null)
			emp.setName(name);
		if(description != null)
			emp.setDescription(description);
		if(street != null)
			emp.getAddress().setStreet(street);
		if(city != null)
			emp.getAddress().setStreet(city);
		if(state != null)
			emp.getAddress().setStreet(state);
		if(zip != null)
			emp.getAddress().setStreet(zip);
		employerService.updateEmployer(emp, id);
		return ResponseEntity.ok(emp);
	}
	
	/**
	 * This function handles the API call to delete a specific employer 
	 * @param id the function accepts the employer id as its sole parameter
	 * @return relevant response code
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.DELETE, value="/employers/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteEmployer(@PathVariable long id) {
		try {
			if(employerService.getEmployer(id) == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			List<Employee> list = employeeService.getAllEmployees();
			boolean foundEmployee = false;
			for(Employee e: list) {
				if(e.getEmployer().getId() == id) {
					foundEmployee = true;
					break;
				}
			}
			
			if(foundEmployee)
				return ResponseEntity.badRequest().build();
				
			employerService.deleteEmployer(id);	
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.EmptyResultDataAccessException(0).getClass())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
		}
		return ResponseEntity.noContent().build();
	}

}
