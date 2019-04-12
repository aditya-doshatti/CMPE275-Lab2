package edu.sjsu.cmpe275.lab2.employee;

import java.util.ArrayList;
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

import edu.sjsu.cmpe275.lab2.employer.*;
import edu.sjsu.cmpe275.lab2.address.Address;
import edu.sjsu.cmpe275.lab2.employee.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployerService employerService;
	
	
	@RequestMapping("/employee")
	public ResponseEntity<Object> getAllEmployees() {
		List<Employee> emps = employeeService.getAllEmployees();
		List <EmployeeResponseMap> retVal = new ArrayList<>();
		for (Employee emp: emps) {
			EmployeeResponseMap emap = new EmployeeResponseMap();
			emap.setId(emp.getId());
			emap.setName(emp.getName());
			emap.setEmail(emp.getEmail());
			emap.setTitle(emp.getTitle());
			EmployerResponseMap tempEmp = new EmployerResponseMap();
			tempEmp.setName(emp.getEmployer().getName());
			tempEmp.setId(emp.getEmployer().getId());
			if (emp.getManager() != null) {
				ManagerResponseMap TempMang = new ManagerResponseMap();
				TempMang.setName(emp.getManager().getName());
				TempMang.setId(emp.getManager().getId());
				TempMang.setTitle(emp.getManager().getTitle());
				emap.setManager(TempMang);
			}
			emap.setEmployer(tempEmp);
			List<CollaboratorsMap> collab = new ArrayList<>();
			for(Employee e: emp.getCollaborators()) {
				CollaboratorsMap cb = new CollaboratorsMap();
				cb.setId(e.getId());
				cb.setName(e.getName());
				cb.setTitle(e.getTitle());
				EmployerResponseMap tempEmp1 = new EmployerResponseMap();
				tempEmp1.setId(e.getEmployer().getId());
				tempEmp1.setName(e.getEmployer().getName());
				cb.setEmployer(tempEmp1);
				collab.add(cb);
			}
			List<ManagerResponseMap> report = new ArrayList<>();
			for(Employee e: emp.getReports()) {
				ManagerResponseMap managerMap = new ManagerResponseMap();
				managerMap.setId(e.getId());
				managerMap.setName(e.getName());
				managerMap.setTitle(e.getTitle());
				report.add(managerMap);
			}
			emap.setCollaborators(collab);
			emap.setReports(report);
			retVal.add(emap);
		}
		return ResponseEntity.ok(retVal);
		//return emps;
	}
	
	@RequestMapping("/employee/{id}")
	public ResponseEntity<Object> getEmployee(@PathVariable long id) {
		Employee emp = employeeService.getEmployee(id);
		EmployeeResponseMap emap = new EmployeeResponseMap();
		emap.setId(emp.getId());
		emap.setName(emp.getName());
		emap.setEmail(emp.getEmail());
		emap.setTitle(emp.getTitle());
		EmployerResponseMap tempEmp = new EmployerResponseMap();
		tempEmp.setName(emp.getEmployer().getName());
		tempEmp.setId(emp.getEmployer().getId());
		if (emp.getManager() != null) {
			ManagerResponseMap TempMang = new ManagerResponseMap();
			TempMang.setName(emp.getManager().getName());
			TempMang.setId(emp.getManager().getId());
			TempMang.setTitle(emp.getManager().getTitle());
			emap.setManager(TempMang);
		}
		emap.setEmployer(tempEmp);
		List<CollaboratorsMap> collab = new ArrayList<>();
		for(Employee e: emp.getCollaborators()) {
			CollaboratorsMap cb = new CollaboratorsMap();
			cb.setId(e.getId());
			cb.setName(e.getName());
			cb.setTitle(e.getTitle());
			EmployerResponseMap tempEmp1 = new EmployerResponseMap();
			tempEmp1.setId(e.getEmployer().getId());
			tempEmp1.setName(e.getEmployer().getName());
			cb.setEmployer(tempEmp1);
			collab.add(cb);
		}
		List<ManagerResponseMap> report = new ArrayList<>();
		for(Employee e: emp.getReports()) {
			ManagerResponseMap managerMap = new ManagerResponseMap();
			managerMap.setId(e.getId());
			managerMap.setName(e.getName());
			managerMap.setTitle(e.getTitle());
			report.add(managerMap);
		}
		emap.setCollaborators(collab);
		emap.setReports(report);
		return ResponseEntity.ok(emap);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee")
	public void addEmployee(@RequestParam String name
            , @RequestParam String email
            , @RequestParam(required = false) String title
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip
            , @RequestParam Long employerId
            , @RequestParam(required = false) Long managerId
            ) {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setEmail(email);
		emp.setTitle(title);
		emp.setAddress(new Address(street, city, state, zip));
		emp.setEmployer(employerService.getEmployer(employerId));
		emp.setManager(employeeService.getEmployee(managerId));
		//emp.setEmployer();
		employeeService.addEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employers/{employerId}/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable long id, @RequestParam String name
            , @RequestParam String email
            , @RequestParam(required = false) String title
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip
            , @RequestParam Long employerId
            , @RequestParam(required = false) Long managerId
            ) {
		Employee emp = employeeService.getEmployee(id);
		if(emp ==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		if (name != null)
			emp.setName(name);
		if (email != null)
			emp.setEmail(email);
		if (title != null)
			emp.setTitle(title);
		if (employerId != null)
			emp.setEmployer(employerService.getEmployer(employerId));
		if (managerId != null)
			emp.setManager(employeeService.getEmployee(managerId));
		employeeService.updateEmployee(emp);
		return ResponseEntity.ok(emp);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
	public ResponseEntity<Object> deleteEmployer(@PathVariable long id) {
		try {
			employeeService.deleteEmployee(id);
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
	
	@RequestMapping(method=RequestMethod.PUT, value="/collaborator/{id1}/{id2}")
	public ResponseEntity<Object> addCollaboration(@PathVariable long id1, @PathVariable long id2) {
		try {
			Employee emp1 = employeeService.getEmployee(id1);
			Employee emp2 = employeeService.getEmployee(id2);
			emp1.getPersons().add(emp2);
			emp2.getPersons().add(emp1);
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
	
	@RequestMapping(method=RequestMethod.DELETE, value="/collaborator/{id1}/{id2}")
	public ResponseEntity<Object> removeCollaboration(@PathVariable long id1, @PathVariable long id2) {
		try {
			Employee emp1 = employeeService.getEmployee(id1);
			Employee emp2 = employeeService.getEmployee(id2);
			emp1.getPersons().remove(emp2);
			emp2.getPersons().remove(emp1);
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
