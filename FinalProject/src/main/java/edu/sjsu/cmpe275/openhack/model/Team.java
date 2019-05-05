package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private long teamId;
	
	@Column(nullable=false, unique=true)
	String name;

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
	
	public Team() { }
	
	public Team(String name) {
		this.name = name;
	}
	
	public Team(Team obj) {
		this.name = obj.name;
	}
}
