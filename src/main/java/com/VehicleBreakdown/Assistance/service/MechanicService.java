package com.VehicleBreakdown.Assistance.service;

import java.util.List;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;


public interface MechanicService {
	public Mechanic mechanicRegistration(Mechanic mechanic);
	
	public List<AssistanceRequired> viewRequest(long mechanicId);

	public List<Feedback> viewFeedback(long mechanicId);

	//public boolean loginMechanic(String email, String password);

	//public boolean getMechanic(String email);
	
	// getallmechanic
}
