package edu.sjsu.cmpe275.openhack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author adityadoshatti
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="screenName")
	private String screenName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email",nullable = false, unique = true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="portraitUrl")
	private String portraitUrl;
	
	@Column(name="businessTitle")
	private String businessTitle;
	
	@Column(name="aboutMe")
	private String aboutMe;
	
	@Column(name="address")
	private String address;
	
	@Column(name="isVerified")
	@org.hibernate.annotations.ColumnDefault("false")
	private String isVerified;
	
	@Column(name="role")
//	@org.hibernate.annotations.ColumnDefault("hacker")
	private String role;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;
	
	public User() {
		
	}
	
	public User(User user) {
		super();
		this.screenName=user.screenName;
		this.name = user.name;
		this.email=user.email;
		this.isVerified="false";
		this.password=user.password;
		this.aboutMe=user.aboutMe;
		this.businessTitle=user.businessTitle;
		this.portraitUrl=user.portraitUrl;
		this.address=user.address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	
	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	

}
