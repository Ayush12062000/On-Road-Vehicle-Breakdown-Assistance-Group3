package com.VehicleBreakdown.Assistance.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mechanic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mechanicId;
	
	private String mechanicName;
	private long mechanicPhoneNumber;
	private String mechanicEmailId;
	private String mechanicLocation;
	private String mechanicPassword;
	
	@OneToMany(mappedBy="mechanic",cascade = CascadeType.MERGE)
	private List<Feedback> feedback;
	
	public Mechanic() {}
	
	public Mechanic(long mechanicId, String mechanicName, long mechanicPhoneNumber, String mechanicEmailId,
			String mechanicLocation, String mechanicPassword, List<Feedback> feedback) {
		this.mechanicId = mechanicId;
		this.mechanicName = mechanicName;
		this.mechanicPhoneNumber = mechanicPhoneNumber;
		this.mechanicEmailId = mechanicEmailId;
		this.mechanicLocation = mechanicLocation;
		this.mechanicPassword = mechanicPassword;
		this.feedback = feedback;
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


	@Override
	public String toString() {
		return "Mechanic [mechanicId=" + mechanicId + ", mechanicName=" + mechanicName + ", mechanicPhoneNumber="
				+ mechanicPhoneNumber + ", mechanicEmailId=" + mechanicEmailId + ", mechanicLocation="
				+ mechanicLocation + ", mechanicPassword=" + mechanicPassword + ", feedback=" + feedback + "]";
	}
	
}
