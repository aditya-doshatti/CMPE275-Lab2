package edu.sjsu.cmpe275.openhack.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.openhack.model.TeamUserAssoc;
import edu.sjsu.cmpe275.openhack.model.TeamUserID;

/**
 * 
 * @author pratikb
 *
 */

public interface TeamUserAssocRepository extends JpaRepository<TeamUserAssoc, TeamUserID> {	
	
}
