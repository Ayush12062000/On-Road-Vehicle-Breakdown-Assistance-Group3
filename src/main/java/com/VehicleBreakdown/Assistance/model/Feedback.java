package com.VehicleBreakdown.Assistance.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedbackId;
	
	private String feedbackMessage;
	private int ratings;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "mechanicId"), name = "mechanicId")
	private Mechanic mechanic;
	
	public Feedback() {}

	public Feedback(long feedbackId, String feedbackMessage, int ratings, Mechanic mechanic) {
		this.feedbackId = feedbackId;
		this.feedbackMessage = feedbackMessage;
		this.ratings = ratings;
		this.mechanic = mechanic;
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

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedbackMessage=" + feedbackMessage + ", ratings=" + ratings
				+ ", mechanic=" + mechanic + "]";
	}

}
