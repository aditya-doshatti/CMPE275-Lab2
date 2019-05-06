package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hackathon_sponsor_tbl")
public class HackathonSponsorAssoc {
	
	@EmbeddedId
    private HackathonSponsorId assocId;
	
	@ManyToOne
	@JoinColumn(name = "FK_HACKATHON_ID", insertable = false, updatable = false)
	private Hackathon hackathon;
	
	@ManyToOne
	@JoinColumn(name = "FK_ORGANIZATION_ID", insertable = false, updatable = false)
	private Organization organization;
	
	@Column(name = "discount")
	private Double discount; 
	
	public HackathonSponsorAssoc(Hackathon h, Organization o) {
		assocId = new HackathonSponsorId(h.getId(), o.getId());
		hackathon = h;
		organization = o;
		h.getSponsors().add(this);
		o.getHackathons().add(this);
	}
	
}
