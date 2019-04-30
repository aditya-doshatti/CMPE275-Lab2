package edu.sjsu.cmpe275.openhack.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.openhack.model.User;

/**
 * 
 * @author adityadoshatti
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{

}
