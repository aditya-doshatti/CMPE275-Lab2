package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class HackathonTeamAssoc {
	
	@EmbeddedId
	HackathonTeamID id;
	
	@ManyToOne
	@JoinColumn(name = "FK_HACKATHON_ID", insertable = false, updatable = false)
	Hackathon hackathon;
	
	@ManyToOne
	@JoinColumn(name = "FK_TEAM_ID", insertable = false, updatable = false)
	@JsonIgnoreProperties(value = { "name", "users", "hackathons", "hibernateLazyInitializer", "handler"})
	Team teamObj;
	
	String submissionLink;
	
	String grade;
	
	public HackathonTeamAssoc() { }
	
	

	/**
	 * @param id
	 * @param submissionLink
	 * @param grade
	 */
	public HackathonTeamAssoc(Hackathon hackathon, Team team, String submissionLink, String grade) {
		this.id = new HackathonTeamID(hackathon.getId(), team.getTeamId());
		this.hackathon = hackathon;
		this.teamObj = team;
		this.submissionLink = submissionLink;
		this.grade = grade;
	}


	/**
	 * @return the id
	 */
	public HackathonTeamID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(HackathonTeamID id) {
		this.id = id;
	}

	/**
	 * @return the hackathon
	 */
	public Hackathon getHackathon() {
		return hackathon;
	}

	/**
	 * @param hackathon the hackathon to set
	 */
	public void setHackathon(Hackathon hackathon) {
		this.hackathon = hackathon;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return teamObj;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.teamObj = team;
	}

	/**
	 * @return the submissionLink
	 */
	public String getSubmissionLink() {
		return submissionLink;
	}

	/**
	 * @param submissionLink the submissionLink to set
	 */
	public void setSubmissionLink(String submissionLink) {
		this.submissionLink = submissionLink;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
