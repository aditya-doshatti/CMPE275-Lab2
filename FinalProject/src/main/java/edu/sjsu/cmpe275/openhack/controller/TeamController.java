package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.openhack.model.Hackathon;
import edu.sjsu.cmpe275.openhack.model.Team;
import edu.sjsu.cmpe275.openhack.service.TeamService;

/**
 * 
 * @author pratikb
 *
 */
@RestController
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
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
}
