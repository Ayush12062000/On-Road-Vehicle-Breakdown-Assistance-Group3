package com.VehicleBreakdown.Assistance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.service.FeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/feedback")
@Api(produces="application/json", value="Feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("/all")
	@ApiOperation(value="Get all feedback")
	public List<Feedback> getAllFeedback(){
		return feedbackService.getAllFeedback();
		
	}
}
