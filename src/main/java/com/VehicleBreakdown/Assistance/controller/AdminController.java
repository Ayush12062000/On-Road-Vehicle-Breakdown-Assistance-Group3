package com.VehicleBreakdown.Assistance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.exception.AdminNotFoundException;
import com.VehicleBreakdown.Assistance.exception.FeedbackNotFoundException;
import com.VehicleBreakdown.Assistance.exception.InvalidLoginException;
import com.VehicleBreakdown.Assistance.exception.MechanicNotFoundException;
import com.VehicleBreakdown.Assistance.exception.UserNotFoundException;
import com.VehicleBreakdown.Assistance.model.Admin;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AdminRepository;
import com.VehicleBreakdown.Assistance.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
@Api(produces="application/json", value="Admin Operations")
public class AdminController {
	@Autowired
	public AdminService adminService;
	
	@Autowired
	public AdminRepository adminRepository;
	
	@PostMapping("/register")
	@ApiOperation(value="Admin Signup")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody Admin newAdmin) {
        List<Admin> admins = adminRepository.findAll();
        for (Admin admin : admins) {
            if (admin.equals(newAdmin)) {
            	return new ResponseEntity<String>("Username Already Taken", HttpStatus.BAD_REQUEST);
            }
        }
        adminRepository.save(newAdmin);
        return new ResponseEntity<String>("Registration Successful", HttpStatus.OK);   
    }
    
    @PostMapping("/login")
    @ApiOperation(value="Admin Login")
    public ResponseEntity<String> loginAdmin(@Valid @RequestBody Admin admin) throws AdminNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
            	if(adm.isLoggedIn())
            		return new ResponseEntity<String>("Already Logged in", HttpStatus.BAD_REQUEST);
            	adm.setUsername(admin.getUsername());
            	adm.setPassword(admin.getPassword());
            	adm.setLoggedIn(true);
            	adminService.updateAdmin(adm);
            	return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Invalid Login", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/logout")
    @ApiOperation(value="Admin Logout")
    public ResponseEntity<String> logAdminOut(@Valid @RequestBody Admin admin) throws AdminNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
            	if(!adm.isLoggedIn())
            		return new ResponseEntity<String>("Already Logged out", HttpStatus.BAD_REQUEST);
            	adm.setUsername(admin.getUsername());
            	adm.setPassword(admin.getPassword());
            	adm.setLoggedIn(false);
            	adminService.updateAdmin(adm);
            	return new ResponseEntity<String>("Logout Successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
	
    @GetMapping("/login/showusers/{adminUsername}")
    @ApiOperation(value="Show all users to admin")
	public ResponseEntity<List<User> > getAllUsers(@PathVariable(value="adminUsername") String username) throws Exception{

    	Admin adm = adminService.getAdminByUsername(username).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+username));
    			if(adm.isLoggedIn()) {
    				List<User> viewUser = adminService.getAllUsers();
    				if (viewUser.isEmpty())
    					throw new UserNotFoundException("No Users Found");
    				return new ResponseEntity<List<User> >(viewUser, HttpStatus.OK);
    			}
    			else
    				throw new InvalidLoginException("Login Required");
    }
    
    @GetMapping("/viewFeedback")
    @ApiOperation(value="Show all feedback to admin")
	public ResponseEntity<List<Feedback>> viewFeedback() throws FeedbackNotFoundException {

		List<Feedback> viewFeedback = adminService.viewFeedback();
		if (viewFeedback.isEmpty()) {
			throw new FeedbackNotFoundException("FeedBack not found:");
		}
		return new ResponseEntity<List<Feedback>>(viewFeedback, HttpStatus.OK);
	}
    
    @GetMapping("/login/showmechanics/{adminUsername}")
    @ApiOperation(value="Get all mechanics")
    public ResponseEntity<List<Mechanic> > getAllMechanics(@PathVariable(value="adminUsername") String username) throws Exception{
 
    	Admin adm = adminService.getAdminByUsername(username).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+username));
    	if(adm.isLoggedIn()) {
    		List<Mechanic> viewMechanic = adminService.getAllMechanics();
    		if (viewMechanic.isEmpty())
    				throw new MechanicNotFoundException("No Mechanics Found");
    		return new ResponseEntity<List<Mechanic> >(viewMechanic, HttpStatus.OK);
    		}
    		else
    			throw new InvalidLoginException("Login Required");
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
   	@ExceptionHandler(MethodArgumentNotValidException.class)
   	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
   		Map<String, String> errors = new HashMap<>();
   		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
   		return errors;
       }
    
    @PostMapping("/alloworblockmechanic/{mechanicId}")
    public ResponseEntity<String> allowOrBlock(@PathVariable(value="mechanicId") long mechId)
    {
    	ResponseEntity<String> allowBlock = adminService.allowOrBlockMechanic(mechId);
    	return allowBlock;
    }
    
}
