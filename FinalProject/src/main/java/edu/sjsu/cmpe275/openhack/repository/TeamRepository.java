package edu.sjsu.cmpe275.openhack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.model.User;

/**
 * 
 * @author pratikb
 *
 */

public interface TeamRepository extends JpaRepository<Team, Long> {	
	
}
