package edu.sjsu.cmpe275.openhack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class HackathonTeamID implements Serializable {
	
	@Column(name = "FK_HACKATHON_ID")
	Long hackId;
	
	@Column(name = "FK_TEAM_ID")
	Long teamId;
	
	public HackathonTeamID() { }
	
	public HackathonTeamID(Long hId, Long tId) { hackId = hId; teamId = tId; }
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj instanceof HackathonTeamID)
			return (hackId == ((HackathonTeamID) obj).getHackId() && teamId == ((HackathonTeamID) obj).getTeamId());
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @return the hackId
	 */
	public Long getHackId() {
		return hackId;
	}

	/**
	 * @param hackId the hackId to set
	 */
	public void setHackId(Long hackId) {
		this.hackId = hackId;
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
}
