package com.VehicleBreakdown.Assistance.service;

import java.util.List;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;


public interface MechanicService {
	public Mechanic mechanicRegistration(Mechanic mechanic);
	public Mechanic updateMechanic(Mechanic mechanic);
	public Mechanic getMechanicByMechanicEmailId(String mechanicEmailId);
	public Mechanic getMechanicByMechanicId(Long mechanicId);
	
	public List<AssistanceRequired> viewRequest(long mechanicId);

	public List<Feedback> viewFeedback(long mechanicId);
	

	//public boolean loginMechanic(String email, String password);

	//public boolean getMechanic(String email);
	
	// getallmechanic
	
}
