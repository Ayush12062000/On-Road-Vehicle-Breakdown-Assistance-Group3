package com.VehicleBreakdown.Assistance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.exception.FeedbackNotFoundException;
import com.VehicleBreakdown.Assistance.exception.InvalidLoginException;
import com.VehicleBreakdown.Assistance.exception.MechanicNotFoundException;
import com.VehicleBreakdown.Assistance.exception.RequestNotFoundException;
import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.MechanicLogin;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;
import com.VehicleBreakdown.Assistance.service.MechanicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/mechanic")
@Api(produces="application/json", value="Mechanic Operations")
public class MechanicController {
	
	@Autowired
	private MechanicService mechanicService;
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@PostMapping("/new")
	@ApiOperation(value="Mechanic Signup")
	public Mechanic createNewMechanicRegistration(@Valid @RequestBody Mechanic mechanic)
	{
		return mechanicService.mechanicRegistration(mechanic);
	}
	
	@GetMapping("/viewRequest/{mechId}")
	@ApiOperation(value="View all requests from users")
	public ResponseEntity<List<AssistanceRequired>> viewingRequest(@PathVariable("mechId") long mechanicId) throws RequestNotFoundException, InvalidLoginException, MechanicNotFoundException
	{
		List<AssistanceRequired> requestList = mechanicService.viewRequest(mechanicId);
		if(requestList.isEmpty())
		{
			throw new RequestNotFoundException("Request not found with id:" + mechanicId);
		}
		return new ResponseEntity<List<AssistanceRequired>>(requestList, HttpStatus.OK);
	}
	
	//all mechanic view GET
	@GetMapping("/bymechanicid/{id}")
	@ApiOperation(value="Get mechanic by id")
	public ResponseEntity<Mechanic> getMechanicByMechanicId(@PathVariable(value="id") Long mechanicId)
	{
		Mechanic mechanic = (mechanicService.getMechanicByMechanicId(mechanicId)).orElse(null);
		return ResponseEntity.ok().body(mechanic);
	}
	
	@PutMapping("/update/bymechanicid/{id}")
	@ApiOperation(value="Update existing mechanic")
	public ResponseEntity<Mechanic> updateMechanicByMechanicId(@PathVariable(value="id") Long mechanicId,@Valid @RequestBody Mechanic mechanicinfo)
	{
		Mechanic mechanic = mechanicService.getMechanicByMechanicId(mechanicId).orElse(null);
		mechanic.setMechanicEmailId(mechanicinfo.getMechanicEmailId());
		mechanic.setMechanicPhoneNumber(mechanicinfo.getMechanicPhoneNumber());
		mechanic.setMechanicName(mechanicinfo.getMechanicName());
		mechanic.setMechanicPassword(mechanicinfo.getMechanicPassword());
		mechanic.setMechanicLocation(mechanicinfo.getMechanicLocation());
		Mechanic updatedMechanic = mechanicService.updateMechanic(mechanic);
		return ResponseEntity.ok(updatedMechanic);
	}
	
	@GetMapping("/bymechanicemail/{email}")
	@ApiOperation(value="Get mechanic by email")
	public ResponseEntity<Mechanic> getMechanicByMechanicEmail(@PathVariable(value="email") String mechanicEmailId)
	{
		Mechanic mechanic = mechanicService.getMechanicByMechanicEmailId(mechanicEmailId);
		return ResponseEntity.ok().body(mechanic);
	}
	
	@PostMapping("/login")
	@ApiOperation(value="Mechanic Login")
	public ResponseEntity<String> loginMechanic(@Valid @RequestBody MechanicLogin mechanic) throws MechanicNotFoundException {
		List<Mechanic> mechanics = mechanicRepository.findAll();
	    for (Mechanic other : mechanics) {
	        if (other.getMechanicEmailId().equals(mechanic.getMechanicEmailId()) && other.getMechanicPassword().equals(mechanic.getMechanicPassword())) {
	        	Mechanic m =  mechanicRepository.getMechanicByMechanicEmailId(mechanic.getMechanicEmailId()).orElseThrow(()->new MechanicNotFoundException("No Mechanic Found with this Mechanicname: "+mechanic.getMechanicEmailId()));            	
	        	if(m.isLoggedIn())
	        		return new ResponseEntity<String>("Already Logged in", HttpStatus.BAD_REQUEST);
	        	m.setLoggedIn(true);
	        	mechanicService.updateMechanic(m);
	        	return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
	        }
	    }
	    return new ResponseEntity<String>("Invalid Login", HttpStatus.UNAUTHORIZED);
	}
	    
	@PostMapping("/logout")
	@ApiOperation(value="Mechanic Logout")
	public ResponseEntity<String> logMechanicOut(@Valid @RequestBody MechanicLogin mechanic) throws MechanicNotFoundException {
		List<Mechanic> mechanics = mechanicRepository.findAll();
	    for (Mechanic other : mechanics) {
	     	if (other.getMechanicEmailId().equals(mechanic.getMechanicEmailId()) && other.getMechanicPassword().equals(mechanic.getMechanicPassword())) {            	
	        	Mechanic m =  mechanicRepository.getMechanicByMechanicEmailId(mechanic.getMechanicEmailId()).orElseThrow(()->new MechanicNotFoundException("No Mechanic Found with this Mechanicname: "+mechanic.getMechanicEmailId()));
	           	if(!m.isLoggedIn())
	           		return new ResponseEntity<String>("Already Logged out", HttpStatus.BAD_REQUEST);
	           	m.setLoggedIn(false);
	           	mechanicService.updateMechanic(m);
	           	return new ResponseEntity<String>("Logout Successful", HttpStatus.OK);
	        }
	    }
	    return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
   }
	   
	@GetMapping("/viewFeedback/{mechId}")
	@ApiOperation(value="View Feedback")
	public ResponseEntity<List<Feedback>> viewFeedback(@Valid @PathVariable("mechId") long mechanicId) throws FeedbackNotFoundException {
		
		List<Feedback> viewFeedback = mechanicService.viewFeedback(mechanicId);
		if (viewFeedback.isEmpty()) {
			throw new FeedbackNotFoundException("Feedback not found for the given mechanic id");
		}
		return new ResponseEntity<List<Feedback>>(viewFeedback, HttpStatus.OK);
	}

}
