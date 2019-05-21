package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author pratikb
 *
 */
@Entity
public class HackathonExpense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName = "HACKATHON_ID")
	private Hackathon hackathon;
	
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="TIME", nullable=false)
	private String time;
	
	@Column(name="AMOUNT", nullable=false)
	private Double amount;
	
	public HackathonExpense() { }
	
	public HackathonExpense(HackathonExpense obj) {
		this.hackathon = obj.hackathon;
		this.description = obj.description;
		this.time = obj.time;
		this.amount = obj.amount;
	}

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
	 * @return the hackathon
	 */
	@JsonIgnoreProperties(value = {"teams","judges","sponsors", "hibernateLazyInitializer", "handler"}, allowSetters=true)
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
