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
 * Class to handle /employee request-path
 *
 */
@RestController
public class EmployeeController {
	/**
	 * EmployeeService and EmployerService objects are autowired for implicit dependency injection 
	 */
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployerService employerService;
	
	/**
	 * This function is to handle the API call for getting information of all employees along with appropriate response code
	 * @return ResponseEntity Object
	 */
	@RequestMapping(value = "/employee",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getAllEmployees() {
		List<Employee> emps = employeeService.getAllEmployees();
		return ResponseEntity.ok(emps);
	}
	
	/**
	 * This function is to handle the API call for fetching information of a single employee
	 * The function accepts the ID of the employee for whom the information needs to be fetched 
	 * The employee information and a success response code of 200 is returned for valid input
	 * A response code 400 is returned in case the employee doesn't exist
	 * @param id
	 * @return ResponseEntity 
	 */
	@RequestMapping(value = "/employee/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getEmployee(@PathVariable long id) {
		Employee emp =  employeeService.getEmployee(id);
		if (emp!=null)
			return ResponseEntity.ok(emp);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	
	/**
	 * This function is to handle the API call for adding an employee to the employee table.
	 * The function accepts the below mentioned parameters which specify data for the fields in employee table.
	 * The ID is not passed as a parameter as it is auto generated 
	 * Name, employer ID, and email are cumpulsory, other parameters are not.
	 * If the required paramters are missing a 400 is returned, otherwise a success code of 200 is returned.
	 * @param name employee name
	 * @param email employee email
	 * @param title employee title
	 * @param street employee street
	 * @param city employee city
	 * @param state employee state
	 * @param zip employee zip
	 * @param employerId employee employerId
	 * @param managerId employee managerId
	 * @return relevant response code
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/employee", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> addEmployee(@RequestParam String name
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
		Employer tempEmp = employerService.getEmployer(employerId);
		if (tempEmp != null) {
			emp.setEmployer(tempEmp);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Employee tempMang = employeeService.getEmployee(managerId);
		if (tempMang != null) {
			if (tempMang.getEmployer().getId() == employerId) {
				emp.setManager(tempMang);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		employeeService.addEmployee(emp);
		return ResponseEntity.ok(emp);
	}

	/**
	 * This function is to handle the API call for updating the information of employee
	 * @param id employee ID 
	 * @param name employee name
	 * @param email employee email
	 * @param title employee title
	 * @param street employee street
	 * @param city employee city
	 * @param state employee state
	 * @param zip employee zip
	 * @param employerId employee employerId
	 * @param managerId employee managerId
	 * @return relevant response code
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.PUT, value="/employee/{id}", produces = { "application/json", "application/xml" })
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
		if (employerId != null) {
			if (emp.getEmployer() != null && emp.getEmployer().getId() != employerId) {
				Employee thisManager = emp.getManager();
				List<Employee> thisReports = emp.getReports();
				for (Employee e : thisReports) {
					e.setManager(thisManager);
				}
			}
			emp.setEmployer(employerService.getEmployer(employerId));
		}
		if (managerId != null) {
			Employee tempMang = employeeService.getEmployee(managerId);
			if (tempMang != null) {
				if (tempMang.getEmployer().getId() == employerId) {
					emp.setManager(tempMang);
				}
				else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		else {
			emp.setManager(null);
		}
		employeeService.updateEmployee(emp);
		return ResponseEntity.ok(emp);
	}
	
	/**
	 * This function is to handle the API call for deleting an employee 
	 * @param id ID of the employee to be deleted is passed
	 * @return the relevant response code is returned.
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> deleteEmployee(@PathVariable long id) {
		try {
			if(employeeService.getEmployee(id) == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			if (employeeService.getEmployee(id).getReports().size() > 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			Employee emp1 = employeeService.getEmployee(id);
			List<Employee> collab = emp1.getCollaborators();
			for (Employee e: collab) {
				e.getCollaborators().remove(emp1);
				employeeService.updateEmployee(e);
			}
			emp1.getCollaborators().clear();
			employeeService.updateEmployee(emp1);
			employeeService.deleteEmployee(id);
			return ResponseEntity.ok(emp1);
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
