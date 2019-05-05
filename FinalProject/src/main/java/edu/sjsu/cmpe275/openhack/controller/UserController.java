package edu.sjsu.cmpe275.openhack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.openhack.model.Organization;
import edu.sjsu.cmpe275.openhack.model.User;
import edu.sjsu.cmpe275.openhack.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET,value = "/user/ping")
	public String pingHandler() {
		return "Hello! User here!";
	}
	
	@CrossOrigin(origins="*",allowedHeaders="*")
	@RequestMapping(method=RequestMethod.POST, value="/user/signup",  produces = { "application/json", "application/xml" })
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			User tempUser=new User(user);
			userService.addUser(tempUser);
			return ResponseEntity.ok(tempUser);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user/profile/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<User> getProfile(@PathVariable String id ) {
		User user = userService.getProfile(id);
		if  (user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user/profile/sn/{sid}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<User> getProfileByScreenName(@PathVariable String sid ) {
		User user = userService.getProfileByScreenName(sid);
		if  (user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/user/profile/{id}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<User> updateProfile(@PathVariable Long id,@RequestBody User user) {
		try {
			
			User tmpuser=userService.getUser(id);
			tmpuser.setName(user.getName());
			tmpuser.setPortraitUrl(user.getPortraitUrl());
			tmpuser.setBusinessTitle(user.getBusinessTitle());
			tmpuser.setAddress(user.getAddress());
			tmpuser.setAboutMe(user.getAboutMe());
			userService.updateProfile(tmpuser);
			return ResponseEntity.ok(tmpuser);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

}
