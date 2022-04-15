package com.VehicleBreakdown.Assistance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.repository.AssistanceRequiredRepository;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;

@Service
public class MechanicServiceImpl implements MechanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired
	private AssistanceRequiredRepository assistanceRequiredRepository;
	
	@Override
	public Mechanic mechanicRegistration(Mechanic mechanic) {
		return mechanicRepository.save(mechanic);
	}

	// getallmechanic
	
	@Override
	public List<AssistanceRequired> viewRequest(long mechanicId) {
		return assistanceRequiredRepository.findByMechanicId(mechanicId);
	}

}
