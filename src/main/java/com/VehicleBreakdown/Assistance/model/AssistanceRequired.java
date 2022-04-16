package com.VehicleBreakdown.Assistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AssistanceRequired {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long assistanceId;
	
	private String assistanceType;

	private long userId;
	
	private long mechanicId;

	private String location;
	
	@OneToOne(mappedBy="assiatnceRequired",cascade = CascadeType.MERGE)
	@JsonIgnore
	private Feedback feedback;
	
	public AssistanceRequired() {}


	public AssistanceRequired(long assistanceId, String assistanceType, long userId, long mechanicId, String location,
			Feedback feedback) {
		this.assistanceId = assistanceId;
		this.assistanceType = assistanceType;
		this.userId = userId;
		this.mechanicId = mechanicId;
		this.location = location;
		this.feedback = feedback;
	}

	public long getAssistanceId() {
		return assistanceId;
	}

	public void setAssistanceId(long assistanceId) {
		this.assistanceId = assistanceId;
	}

	public String getAssistanceType() {
		return assistanceType;
	}

	public void setAssistanceType(String assistanceType) {
		this.assistanceType = assistanceType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "AssistanceRequired [assistanceId=" + assistanceId + ", assistanceType=" + assistanceType + ", userId="
				+ userId + ", mechanicId=" + mechanicId + ", location=" + location + ", feedback=" + feedback + "]";
	}
	
}
