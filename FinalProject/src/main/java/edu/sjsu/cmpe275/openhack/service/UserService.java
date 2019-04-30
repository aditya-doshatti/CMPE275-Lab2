package edu.sjsu.cmpe275.openhack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.openhack.model.User;
import edu.sjsu.cmpe275.openhack.repository.UserRepository;

/**
 * 
 * @author adityadoshatti
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}
}
