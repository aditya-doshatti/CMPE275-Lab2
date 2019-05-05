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
import edu.sjsu.cmpe275.openhack.service.UserService;

/**
 * 
 * @author adityadoshatti
 *
 */
@RestController
public class OrganizationController {
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value = "/organizations", produces = { "application/json", "application/xml" })
	public List<Organization> getAllOrganizations() {
		return organizationService.getAllOrganizations();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 
	 * @param org
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/organization",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Organization> addOrganization(@RequestBody Organization org) {
		try {
			Organization tempOrg = new Organization(org);
			tempOrg.setOwner(userService.getUser(org.getOwner().getId()));
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
	
	@RequestMapping(method=RequestMethod.PUT, value="/organization/{orgId}/join/{userId}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Organization> joinOrganization(@PathVariable Long orgId, @PathVariable Long userId) {
		try {
			Organization tempOrg = organizationService.getAnOrganizations(orgId);
			List<edu.sjsu.cmpe275.openhack.model.User> tempList = tempOrg.getOrgUsers();
			tempList.add(userService.getUser(userId));
			tempOrg.setOrgUsers(tempList);
			return ResponseEntity.ok(tempOrg);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/organization/{orgId}/leave/{userId}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<Organization> leaveOrganization(@PathVariable Long orgId, @PathVariable Long userId) {
		try {
			Organization tempOrg = organizationService.getAnOrganizations(orgId);
			List<edu.sjsu.cmpe275.openhack.model.User> tempList = tempOrg.getOrgUsers();
			tempList.remove(userService.getUser(userId));
			tempOrg.setOrgUsers(tempList);
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
