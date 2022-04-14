package com.VehicleBreakdown.Assistance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;

@Service
public class MechanicServiceImpl implements MechanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Override
	public Mechanic mechanicRegistration(Mechanic mechanic) {
		return mechanicRepository.save(mechanic);
	}

}
