package edu.sjsu.cmpe275.openhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.openhack.model.Hackathon;
import edu.sjsu.cmpe275.openhack.service.HackathonService;

/**
 * 
 * @author pratikb
 *
 */

@RestController
public class HackathonController {
	
	@Autowired
	HackathonService hackathonService;
	
	@RequestMapping(method=RequestMethod.GET,value = "/hackathons", produces = { "application/json", "application/xml" })
	public List<Hackathon> getAllHackathons() {
		return hackathonService.getAllHackathons();
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/hackathon", produces = { "application/json", "application/xml" })
	public ResponseEntity<Hackathon> createHackathon(@RequestBody Hackathon h) {
		Hackathon temp = new Hackathon(h);
		hackathonService.addHackathon(temp);
		return ResponseEntity.ok(temp);
	}
}
