package edu.sjsu.cmpe275.openhack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class TeamUserID implements Serializable {
	
	@Column(name = "FK_TEAM_ID")
	Long teamid;
	
	@Column(name = "FK_USER_ID")
	Long userid;
	
	public TeamUserID() { }
	
	public TeamUserID(Long teamid, Long userid) {
		this.teamid = teamid;
		this.userid = userid;
	}

	/**
	 * @return the teamid
	 */
	public Long getTeamid() {
		return teamid;
	}

	/**
	 * @param teamid the teamid to set
	 */
	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj instanceof TeamUserID)
			return (teamid == ((TeamUserID) obj).getTeamid() && userid == ((TeamUserID) obj).userid);
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
