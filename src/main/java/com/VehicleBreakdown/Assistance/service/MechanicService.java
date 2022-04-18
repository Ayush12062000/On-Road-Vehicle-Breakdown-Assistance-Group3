package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import com.VehicleBreakdown.Assistance.exception.BlockByAdminException;
import com.VehicleBreakdown.Assistance.exception.InvalidLoginException;
import com.VehicleBreakdown.Assistance.exception.MechanicNotFoundException;
import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;



public interface MechanicService {
	public Mechanic mechanicRegistration(Mechanic mechanic);
	public Mechanic updateMechanic(Mechanic mechanic);
	public Mechanic getMechanicByMechanicEmailId(String mechanicEmailId);
	public Optional<Mechanic> getMechanicByMechanicId(Long mechanicId);
	
	public List<AssistanceRequired> viewRequest(long mechanicId) throws InvalidLoginException, MechanicNotFoundException, BlockByAdminException;

	public List<Feedback> viewFeedback(long mechanicId) throws BlockByAdminException, InvalidLoginException;
	

	//public boolean loginMechanic(String email, String password);

	//public boolean getMechanic(String email);
	
	// getallmechanic
	
}
