package edu.sjsu.cmpe275.lab2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Employer;
import edu.sjsu.cmpe275.lab2.service.EmployerService;

@RestController
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	
	@RequestMapping(value = "/employers", produces = { "application/json", "application/xml" })
	public List<Employer> getAllEmployers() {
		return employerService.getAllEmployers();
	}
	
	@RequestMapping(value = "/employers/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Employer> getEmployer(@PathVariable long id) {
		Employer emp =  employerService.getEmployer(id);
		if(emp !=null)
			return ResponseEntity.ok(emp);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employers", produces = { "application/json", "application/xml" })
	public void addEmployer(@RequestParam String name
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
	}
	
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
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employers/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteEmployer(@PathVariable long id) {
		try {
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
