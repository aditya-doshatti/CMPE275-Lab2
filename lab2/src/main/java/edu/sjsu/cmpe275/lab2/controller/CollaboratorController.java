package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.model.Employee;
import edu.sjsu.cmpe275.lab2.service.EmployeeService;


/**
 * Class for handling API calls related to collaborations
 */
@RestController
public class CollaboratorController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Name: addCollaboration
	 * Handles the request for adding collaboration between employees
	 * it accepts the IDs of the employees between which the collaboration is being added
	 * Depending on the success or failure, relevant response status is returned on API call in the form of response entity
	 * If either employee does not exist, then a 404 is returned.
	 * If collaboration exists between employees, then nothing will be done and 200 status code will be returned.
	 * Else, the collaboration is recorded and response code of 200 is returned
	 * @param id1 id of first collaborator
	 * @param id2 id of second collaborator
	 * @return relevant response code
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.PUT, value="/collaborators/{id1}/{id2}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> addCollaboration(@PathVariable long id1, @PathVariable long id2) {
		try {
			Employee emp1 = employeeService.getEmployee(id1);
			Employee emp2 = employeeService.getEmployee(id2);
			if(emp1 == null || emp2 == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			emp1.setCollaborators(emp2);
			emp2.setCollaborators(emp1);
			employeeService.addCollaboration(emp1, emp2);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.EmptyResultDataAccessException(0).getClass())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
		}
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Name: removeCollaboration
	 * This function is executed on receiving the API call for removing collaboration between employees
	 * It accepts the IDs of collaborators as input
	 * If either employee does not exist, 404 is returned.
	 * 404 is also returned ff the two employees are not collaborators.
	 * Incase everything is valid, the collaboration will be removed and a response code of 200 will be returned
	 * @param id1 id of first collaborator
	 * @param id2 id of second collaborator
	 * @return relevant response code
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.DELETE, value="/collaborators/{id1}/{id2}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> removeCollaboration(@PathVariable long id1, @PathVariable long id2) {
		try {
			Employee emp1 = employeeService.getEmployee(id1);
			Employee emp2 = employeeService.getEmployee(id2);
			if(emp1 == null || emp2 == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			if(!emp1.getCollaborators().contains(emp2) || !emp2.getCollaborators().contains(emp1))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			emp1.getCollaborators().remove(emp2);
			emp2.getCollaborators().remove(emp1);
			employeeService.addCollaboration(emp1, emp2);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.EmptyResultDataAccessException(0).getClass())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.ok().build();
	}
}