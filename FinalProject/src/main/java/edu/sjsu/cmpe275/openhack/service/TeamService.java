package edu.sjsu.cmpe275.openhack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.repository.TeamRepository;

/**
 * 
 * @author pratikb
 *
 */
public class TeamService {
	
	@Autowired
	TeamRepository teamRepository;
	
	public List<Team> getAllTeams() {
		List<Team> teamList = new ArrayList<Team>();
		teamRepository.findAll().forEach(teamList::add);
		return teamList;
	}

}
