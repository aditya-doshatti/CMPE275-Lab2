package edu.sjsu.cmpe275.openhack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.openhack.model.HackathonTeamAssoc;
import edu.sjsu.cmpe275.openhack.model.HackathonTeamID;



public interface HackathonTeamAssocRepository extends JpaRepository<HackathonTeamAssoc, HackathonTeamID> {

}
