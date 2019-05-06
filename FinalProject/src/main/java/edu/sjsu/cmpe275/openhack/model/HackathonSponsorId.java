package edu.sjsu.cmpe275.openhack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author pratikb
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class HackathonSponsorId implements Serializable {

	@Column(name = "FK_HACKATHON_ID")
	private Long hackathon;
	
	@Column(name = "FK_ORGANIZATION_ID")
	private Long organization;

	public HackathonSponsorId(long hackId, Long orgId) {
		hackathon = hackId;
		organization = orgId;
	}

	/**
	 * @return the hackathon
	 */
	public Long getHackathon() {
		return hackathon;
	}

	/**
	 * @param hackathon the hackathon to set
	 */
	public void setHackathon(Long hackathon) {
		this.hackathon = hackathon;
	}

	/**
	 * @return the organization
	 */
	public Long getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	@Override
	public boolean equals(Object obj) {
		return (hackathon == ((HackathonSponsorId)obj).hackathon && organization == ((HackathonSponsorId)obj).organization);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	} 
}
