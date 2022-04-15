package com.VehicleBreakdown.Assistance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.exception.RequestNotFoundException;
import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
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
	
	@GetMapping("/viewRequest/{mechId}")
	public ResponseEntity<List<AssistanceRequired>> viewingRequest(@PathVariable("mechId") int mechanicId) throws RequestNotFoundException
	{
		List<AssistanceRequired> requestList = mechanicService.viewRequest(mechanicId);
		if(requestList.isEmpty())
		{
			throw new RequestNotFoundException("Request not found with id:" + mechanicId);
		}
		return new ResponseEntity<List<AssistanceRequired>>(requestList, HttpStatus.OK);
	}
	
	//all mechanic view GET
}
