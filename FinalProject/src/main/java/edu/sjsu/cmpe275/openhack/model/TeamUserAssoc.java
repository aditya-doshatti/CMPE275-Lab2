package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeamUserAssoc {

	@EmbeddedId
	TeamUserID id;
	
	@ManyToOne
	@JoinColumn(name = "FK_TEAM_ID", insertable = false, updatable = false)
	Team team;
	
	@ManyToOne
	@JoinColumn(name = "FK_USER_ID", insertable = false, updatable = false)
	User user;
	
	@Column(nullable=false)
	String role = "Other";
	
	public TeamUserAssoc() { }

	/**
	 * @param id
	 * @param role
	 */
	public TeamUserAssoc(Team team, User user, String role) {
		id = new TeamUserID(team.getTeamId(), user.getId());
		this.team = team;
		this.user = user;
		this.role = role;
		
		team.getUsers().add(this);
		user.getTeams().add(this);		
	}	
}
