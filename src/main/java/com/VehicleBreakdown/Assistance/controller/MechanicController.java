package com.VehicleBreakdown.Assistance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.service.MechanicService;



@RestController
@RequestMapping("/mechanic")
public class MechanicController {
	
	@Autowired
	private MechanicService mechanicService;
	
	@PostMapping("/new")
	public Mechanic createNewMechanicRegistration(@Valid @RequestBody Mechanic mechanic)
	{
		return mechanicService.mechanicRegistration(mechanic);
	}
}
