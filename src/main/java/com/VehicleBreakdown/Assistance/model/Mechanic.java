package com.VehicleBreakdown.Assistance.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mechanic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mechanicId;
	
	@NotNull
	private String mechanicName;
	
	@NotNull
	private long mechanicPhoneNumber;
	
	@NotEmpty(message="EmailId is required")
	@Email
	private String mechanicEmailId;
	
	@NotNull
	private String mechanicLocation;
	
	@NotEmpty(message="Password is required")
	@Size(min = 5, max = 15, message="Password should be between 8-15 characters")
	private String mechanicPassword;
	
	@JsonIgnore
	private boolean allowed;
	 
	@JsonIgnore
	private boolean loggedIn;
	
	@OneToMany(mappedBy="mechanic",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Feedback> feedback;
	
	public Mechanic() {
		this.loggedIn = false;
		this.allowed = true;
	}
	
	public Mechanic(long mechanicId, String mechanicName, long mechanicPhoneNumber, String mechanicEmailId,
			String mechanicLocation, String mechanicPassword, List<Feedback> feedback) {
		this.mechanicId = mechanicId;
		this.mechanicName = mechanicName;
		this.mechanicPhoneNumber = mechanicPhoneNumber;
		this.mechanicEmailId = mechanicEmailId;
		this.mechanicLocation = mechanicLocation;
		this.mechanicPassword = mechanicPassword;
		this.feedback = feedback;
		this.loggedIn = false;
		this.allowed = true;
	}
	
	public long getMechanicId() {
		return mechanicId;
	}


	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}


	public String getMechanicName() {
		return mechanicName;
	}


	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}


	public long getMechanicPhoneNumber() {
		return mechanicPhoneNumber;
	}


	public void setMechanicPhoneNumber(long mechanicPhoneNumber) {
		this.mechanicPhoneNumber = mechanicPhoneNumber;
	}


	public String getMechanicEmailId() {
		return mechanicEmailId;
	}


	public void setMechanicEmailId(String mechanicEmailId) {
		this.mechanicEmailId = mechanicEmailId;
	}


	public String getMechanicLocation() {
		return mechanicLocation;
	}


	public void setMechanicLocation(String mechanicLocation) {
		this.mechanicLocation = mechanicLocation;
	}


	public String getMechanicPassword() {
		return mechanicPassword;
	}


	public void setMechanicPassword(String mechanicPassword) {
		this.mechanicPassword = mechanicPassword;
	}


	public List<Feedback> getFeedback() {
		return feedback;
	}


	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	
	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
}
