package edu.sjsu.cmpe275.openhack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.model.TeamUserAssoc;
import edu.sjsu.cmpe275.openhack.model.User;
import edu.sjsu.cmpe275.openhack.repository.TeamUserAssocRepository;

@Service
public class TeamUserAssocService {

	@Autowired
	TeamUserAssocRepository teamUserAssocRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	public void addTeamUserAssoc(TeamUserAssoc obj) {
		teamUserAssocRepo.save(obj);
	}
	
	public List<User> getUsersInTeam(Long teamId) {
		List<User> users = new ArrayList<User>();
		Set<TeamUserAssoc> tempSet =(Set<TeamUserAssoc>) teamService.getTeamById(teamId).getUsers(); 
	    for(TeamUserAssoc obj : new ArrayList<TeamUserAssoc>(tempSet)) {
	    	User temp = userService.getUser(obj.getId().getUserid());
	    	if(temp != null)
	    		users.add(temp);
	    }
	    return users;
	}
	
	public List<Team> getTeamsOfUser(Long userId) {
		List<Team> teams = new ArrayList<Team>();
		Set<TeamUserAssoc> tempSet =(Set<TeamUserAssoc>) userService.getUser(userId).getTeams();
	    for(TeamUserAssoc obj : new ArrayList<TeamUserAssoc>(tempSet)) {
	    	Team temp = teamService.getTeamById(obj.getId().getTeamid());
	    	if(temp != null)
	    		teams.add(temp);
	    }
	    return teams;
	}
	
}
