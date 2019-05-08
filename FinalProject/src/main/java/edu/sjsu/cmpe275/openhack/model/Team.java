package edu.sjsu.cmpe275.openhack.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author pratikb
 *
 */
@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TEAM_ID")
	private Long teamId;
	
	@Column(nullable=false, unique=true)
	String name;
	
	public Team() { }
	
	public Team(String name) {
		this.name = name;
	}
	
	public Team(Team obj) {
		this.name = obj.name;
	}
	
	@OneToMany(mappedBy = "team")
	@JsonIgnore
	private Set<TeamUserAssoc> users = new HashSet<TeamUserAssoc>();
	
	@OneToMany(mappedBy = "teamObj")
	@JsonIgnore
	private Set<HackathonTeamAssoc> hackathons = new HashSet<HackathonTeamAssoc>();

	/**
	 * @return the hackathons
	 */
	public Set<HackathonTeamAssoc> getHackathons() {
		return hackathons;
	}

	/**
	 * @param hackathons the hackathons to set
	 */
	public void setHackathons(Set<HackathonTeamAssoc> hackathons) {
		this.hackathons = hackathons;
	}

	/**
	 * @return the teamId
	 */
	public Long getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the users
	 */
	public Set<TeamUserAssoc> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<TeamUserAssoc> users) {
		this.users = users;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
