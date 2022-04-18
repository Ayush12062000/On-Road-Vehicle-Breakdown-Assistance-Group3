package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.exception.BlockByAdminException;
import com.VehicleBreakdown.Assistance.exception.InvalidLoginException;
import com.VehicleBreakdown.Assistance.exception.MechanicNotFoundException;
import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AssistanceRequiredRepository;
import com.VehicleBreakdown.Assistance.repository.FeedbackRepository;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;

@Service
public class MechanicServiceImpl implements MechanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired
	private AssistanceRequiredRepository assistanceRequiredRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public Mechanic mechanicRegistration(Mechanic mechanic) {
		return mechanicRepository.save(mechanic);
	}
	
	@Override
	public List<AssistanceRequired> viewRequest(long mechanicId) throws InvalidLoginException, MechanicNotFoundException, BlockByAdminException {
		Mechanic mechanic= mechanicRepository.getById(mechanicId);
		if(!mechanic.isLoggedIn())
		{
			throw new InvalidLoginException("Mechanic is not logged in ,please log in first");
		}
		if(!mechanic.isAllowed())
		{
			throw new BlockByAdminException("You cannot view Request, You are blocked by Admin, Contact Admin for futher Details");
		}
		return assistanceRequiredRepository.findByMechanicId(mechanicId);
	}

	@Override
	public List<Feedback> viewFeedback(long mechanicId) throws BlockByAdminException, InvalidLoginException {
		Mechanic mechanic=mechanicRepository.getById(mechanicId);
		if(!mechanic.isLoggedIn())
		{
			throw new InvalidLoginException("Mechanic is not logged in ,please log in first");
		}
		if(!mechanic.isAllowed())
		{
			throw new BlockByAdminException("You cannot view Feedback, You are blocked by Admin, Contact Admin for futher Details");
		}
		return feedbackRepository.findByMechanic(mechanic);
	}
	@Override
	public Mechanic updateMechanic(Mechanic mechanic) {
		return mechanicRepository.save(mechanic);
	}
	public Mechanic getMechanicByMechanicEmailId(String mechanicEmailId) {
		return mechanicRepository.findByMechanicEmailId(mechanicEmailId);
}
	public Optional<Mechanic> getMechanicByMechanicId(Long mechanicId) {
		return mechanicRepository.findById(mechanicId);
	}

}
