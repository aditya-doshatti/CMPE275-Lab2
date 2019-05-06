package edu.sjsu.cmpe275.openhack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author pratikb
 *
 */
@Entity
public class Hackathon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="HACKATHON_ID")
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(nullable=false)
	private Double regFees;
	
	@Column(nullable=false)
	private boolean isOpen;
	
	@Column(nullable=false)
	private int minTeamSize;
	
	@Column(nullable=false)
	private int maxTeamSize;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name="hackathon_judge",
		joinColumns={@JoinColumn(name="HACKATHON_ID", referencedColumnName="HACKATHON_ID")},
		inverseJoinColumns={@JoinColumn(name="JUDGE_ID", referencedColumnName="USER_ID")})
	private List<User> judges;
	
	@OneToMany(mappedBy = "hackathon")
	private Set<HackathonSponsorAssoc> sponsors = new HashSet<HackathonSponsorAssoc>();
	
	/**
	 * @return the sponsors
	 */
	public Set<HackathonSponsorAssoc> getSponsors() {
		return sponsors;
	}

	/**
	 * @param sponsors the sponsors to set
	 */
	public void setSponsors(Set<HackathonSponsorAssoc> sponsors) {
		this.sponsors = sponsors;
	}

	public Hackathon() { }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the desc
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDescription(String desc) {
		this.description = desc;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the regFees
	 */
	public Double getRegFees() {
		return regFees;
	}

	/**
	 * @param regFees the regFees to set
	 */
	public void setRegFees(Double regFees) {
		this.regFees = regFees;
	}

	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * @return the minTeamSize
	 */
	public int getMinTeamSize() {
		return minTeamSize;
	}

	/**
	 * @param minTeamSize the minTeamSize to set
	 */
	public void setMinTeamSize(int minTeamSize) {
		this.minTeamSize = minTeamSize;
	}

	/**
	 * @return the maxTeamSize
	 */
	public int getMaxTeamSize() {
		return maxTeamSize;
	}

	/**
	 * @param maxTeamSize the maxTeamSize to set
	 */
	public void setMaxTeamSize(int maxTeamSize) {
		this.maxTeamSize = maxTeamSize;
	}

	/**
	 * @return the judges
	 */
	/*
	 * public List<User> getJudges() { return judges; }
	 */

	/**
	 * @param judges the judges to set
	 */
	public void setJudges(List<User> judges) {
		this.judges = judges;
	}
	
	public List<User> getJudges() {
		return this.judges;
	}
	
	public void addJudge(User judge) {
		if(judges == null)
			judges = new ArrayList<User>();
		judges.add(judge);
	}

	/**
	 * @param name
	 * @param desc
	 * @param startDate
	 * @param endDate
	 * @param regFees
	 * @param minTeamSize
	 * @param maxTeamSize
	 */
	public Hackathon(String name, String desc, Date startDate, Date endDate, Double regFees, int minTeamSize,
			int maxTeamSize) {
		super();
		this.name = name;
		this.description = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regFees = regFees;
		this.minTeamSize = minTeamSize;
		this.maxTeamSize = maxTeamSize;
	}
	
	public Hackathon(Hackathon obj) {
		super();
		this.name = obj.name;
		this.description = obj.description;
		this.startDate = obj.startDate;
		this.endDate = obj.endDate;
		this.regFees = obj.regFees;
		this.minTeamSize = obj.minTeamSize;
		this.maxTeamSize = obj.maxTeamSize;
	}

	
	
}
