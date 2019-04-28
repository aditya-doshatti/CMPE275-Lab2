package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.openhack.model.Organization;
import edu.sjsu.cmpe275.openhack.service.OrganizationService;

/**
 * 
 * @author adityadoshatti
 *
 */
@RestController
public class OrganizationController {
	
	@Autowired
	OrganizationService organizationService;
	
	@RequestMapping(method=RequestMethod.GET,value = "/organizations", produces = { "application/json", "application/xml" })
	public List<Organization> getAllOrganizations() {
		return organizationService.getAllOrganizations();
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/organizations/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Organization> getAnOrganizations(@PathVariable Long id) {
		Organization org = organizationService.getAnOrganizations(id);
		if  (org != null) {
			return ResponseEntity.ok(org);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/organziation",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Organization> addOrganization(@RequestBody Organization org) {
		try {
			Organization tempOrg = new Organization(org);
			organizationService.addOrganization(tempOrg);
			return ResponseEntity.ok(tempOrg);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	

}
