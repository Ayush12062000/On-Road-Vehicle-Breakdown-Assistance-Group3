package com.VehicleBreakdown.Assistance.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedbackId;
	
	@NotNull
	private long userId;
	@NotEmpty(message="FeedbackMessage is required")
	private String feedbackMessage;
	
	@NotNull
	@Min(value = 0, message = "Ratings not given")
    @Max(value = 5, message = "Ratings can be given from 1 to 5 only ")
	private int ratings;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mechanicId")
	@JsonIgnore
	private Mechanic mechanic;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assistanceId")
	@JsonIgnore
	private AssistanceRequired assiatnceRequired;
	
	public Feedback() {}
	
	public Feedback(long feedbackId, long userId, String feedbackMessage, int ratings, Mechanic mechanic,
			AssistanceRequired assiatnceRequired) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.feedbackMessage = feedbackMessage;
		this.ratings = ratings;
		this.mechanic = mechanic;
		this.assiatnceRequired = assiatnceRequired;
	}
	

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public AssistanceRequired getAssiatnceRequired() {
		return assiatnceRequired;
	}

	public void setAssiatnceRequired(AssistanceRequired assiatnceRequired) {
		this.assiatnceRequired = assiatnceRequired;
	}
	
}
