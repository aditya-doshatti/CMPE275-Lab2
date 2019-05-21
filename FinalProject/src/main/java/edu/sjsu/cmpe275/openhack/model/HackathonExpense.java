package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"teams","judges","sponsors"})
	@JoinColumn(name="HACK_ID")
	private Hackathon hackathonForExpenses;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="DECRIPTION", nullable=false)
	private String decription;
	
	@Column(name="TIME", nullable=false)
	private String time;
	
	@Column(name="AMOUNT", nullable=false)
	private Double amount;
	
	public HackathonExpense() { }
	
	public HackathonExpense(HackathonExpense obj) {
		this.hackathonForExpenses = obj.hackathonForExpenses;
		this.decription = obj.decription;
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
	 * @return the description
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * @param description the description to set
	 */
	public void setDecription(String description) {
		this.decription = description;
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the hackathonForExpenses
	 */
	@JsonIgnoreProperties(value = {"teams","judges","sponsors", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	public Hackathon getHackathonForExpenses() {
		return hackathonForExpenses;
	}

	/**
	 * @param hackathonForExpenses the hackathonForExpenses to set
	 */
	public void setHackathonForExpenses(Hackathon hackathonForExpenses) {
		this.hackathonForExpenses = hackathonForExpenses;
	}
}
