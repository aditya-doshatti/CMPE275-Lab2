package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.model.User;
import edu.sjsu.cmpe275.openhack.service.MailService;
import edu.sjsu.cmpe275.openhack.service.TeamService;
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
	MailService mailService;
	
	@Autowired
//	TeamUserAssocService teamUserAssocService;
	
	@RequestMapping(method=RequestMethod.GET,value = "/teams", produces = { "application/json", "application/xml" })
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/team", produces = { "application/json", "application/xml" })
	public ResponseEntity<Team> createTeam(@RequestBody Team t) {
		Team temp = new Team(t);
		for(User u:temp.getUsers()) {
			User tempUser = userService.getUser(u.getId());
			try {
				Set<Team> currTeams = tempUser.getParticipantTeam();
				currTeams.add(temp);
				userService.updateProfile(tempUser);
				mailService.sendMail(tempUser.getEmail(),"Payment Due for Openhack", "Go ahead and make payment on http://localhost:8080/payment");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		teamService.addTeam(temp);
		return ResponseEntity.ok(temp);
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/team/{teamId}", produces = { "application/json", "application/xml" })
	public Team getTeamByTeamId(@PathVariable Long teamId) {
		return teamService.getTeamById(teamId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/team/{teamId}/submit", produces = { "application/json", "application/xml" })
	public ResponseEntity<Team> submitLink(@RequestBody Team t, @PathVariable Long teamId) {
		Team temp = new Team(t);
		try {
			teamService.updateTeam(temp);
			return ResponseEntity.ok(temp);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
}
