package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.lab2.model.Employee;
import edu.sjsu.cmpe275.lab2.service.EmployeeService;

@RestController
public class CollaboratorController {

	@Autowired
	private EmployeeService employeeService;
	
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
		return ResponseEntity.ok().body(new ObjectMapper().createObjectNode().put("msg", "Collaboration added"));
	}
	
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
		return ResponseEntity.ok().body(new ObjectMapper().createObjectNode().put("msg", "Collaboration removed"));
	}
}