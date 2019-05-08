package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.model.TeamUserAssoc;
import edu.sjsu.cmpe275.openhack.model.User;
import edu.sjsu.cmpe275.openhack.service.TeamService;
import edu.sjsu.cmpe275.openhack.service.TeamUserAssocService;
import edu.sjsu.cmpe275.openhack.service.UserService;

/**
 * 
 * @author pratikb
 *
 */
@RestController
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TeamUserAssocService teamUserAssocService;
	
	@RequestMapping(method=RequestMethod.GET,value = "/teams", produces = { "application/json", "application/xml" })
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/team", produces = { "application/json", "application/xml" })
	public ResponseEntity<Team> createTeam(@RequestBody Team t) {
		Team temp = new Team(t);
		teamService.addTeam(temp);
		return ResponseEntity.ok(temp);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/team/{userId}/join/{teamId}",  produces = { "application/json", "application/xml" })
	public void joinTeam(@PathVariable Long userId, @PathVariable Long teamId) {
		Team t = teamService.getTeamById(teamId);
		User u = userService.getUser(userId);
		TeamUserAssoc obj = new TeamUserAssoc(t, u, "Other");
		teamUserAssocService.addTeamUserAssoc(obj);	
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/team/{teamId}", produces = { "application/json", "application/xml" })
	public Team getTeamByTeamId(@PathVariable Long teamId) {
		return teamService.getTeamById(teamId);
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/team/{teamId}/users", produces = { "application/json", "application/xml" })
	public List<User> getUsersInATeam(@PathVariable Long teamId) {
		return teamUserAssocService.getUsersInTeam(teamId);
	}
}
