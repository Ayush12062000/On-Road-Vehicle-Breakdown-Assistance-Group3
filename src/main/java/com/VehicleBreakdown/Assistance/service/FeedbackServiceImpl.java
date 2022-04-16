package com.VehicleBreakdown.Assistance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Override
	public List<Feedback> getAllFeedback() {
		return feedbackRepository.findAll();
	}

}
