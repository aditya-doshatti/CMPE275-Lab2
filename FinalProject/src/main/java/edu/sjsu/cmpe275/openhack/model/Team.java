package edu.sjsu.cmpe275.openhack.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
		this.users = obj.users;
		this.hackathon = obj.hackathon;
	}
	
	@ManyToMany(cascade={CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="team_user",
		joinColumns={@JoinColumn(name="TEAM_ID", referencedColumnName="TEAM_ID")},
		inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")})
	@JsonIgnoreProperties(value = {"password", "portraitUrl", "businessTitle", "aboutMe", "address", 
			"judgesHackathons", "teams", "hibernateLazyInitializer", "handler"})
	private Set<User> users;

	@ManyToOne
	private Hackathon hackathon;
	
//	@OneToMany(mappedBy = "team")
//	@JsonIgnore
//	private Set<TeamUserAssoc> users = new HashSet<TeamUserAssoc>();
//	
//	@OneToMany(mappedBy = "teamObj")
//	@JsonIgnore
//	private Set<HackathonTeamAssoc> hackathons = new HashSet<HackathonTeamAssoc>();
//	/**
//	 * @return the users
//	 */
//	public Set<TeamUserAssoc> getUsers() {
//		return users;
//	}
//
//	/**
//	 * @param users the users to set
//	 */
//	public void setUsers(Set<TeamUserAssoc> users) {
//		this.users = users;
//	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
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
	

//	/**
//	 * @return the hackathons
//	 */
//	public Set<HackathonTeamAssoc> getHackathons() {
//		return hackathons;
//	}
//
//	/**
//	 * @param hackathons the hackathons to set
//	 */
//	public void setHackathons(Set<HackathonTeamAssoc> hackathons) {
//		this.hackathons = hackathons;
//	}

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
	

}
