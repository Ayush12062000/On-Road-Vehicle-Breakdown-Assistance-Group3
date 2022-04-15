package com.VehicleBreakdown.Assistance.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedbackId;
	
	private long userId;
	private String feedbackMessage;
	private int ratings;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "mechanicId"), name = "mechanicId")
	private Mechanic mechanic;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "assistanceId"), name = "assistanceId")
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
