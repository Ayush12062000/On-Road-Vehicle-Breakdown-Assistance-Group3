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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.exception.MechanicNotFoundException;
import com.VehicleBreakdown.Assistance.exception.UserNotFoundException;
import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.model.UserLogin;
import com.VehicleBreakdown.Assistance.repository.UserRepository;
import com.VehicleBreakdown.Assistance.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmailId().equals(newUser.getEmailId())) {
            	return new ResponseEntity<String>("Email Already Taken", HttpStatus.BAD_REQUEST);
            }
        }
        userRepository.save(newUser);
        return new ResponseEntity<String>("Registration Successful", HttpStatus.OK);   
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLogin user) throws UserNotFoundException {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.getEmailId().equals(user.getEmailId()) && other.getUserPassword().equals(user.getUserPassword())) {
            	User us =  userRepository.getUserByEmailId(user.getEmailId()).orElseThrow(()->new UserNotFoundException("No User Found with this Username: "+user.getEmailId()));            	if(us.isLoggedIn())
            	if(us.isLoggedIn())
            		return new ResponseEntity<String>("Already Logged in", HttpStatus.BAD_REQUEST);
            	us.setLoggedIn(true);
            	userService.updateUser(us);
            	return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Invalid Login", HttpStatus.UNAUTHORIZED);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logUserOut(@Valid @RequestBody UserLogin user) throws UserNotFoundException {
    	List<User> users = userRepository.findAll();
        for (User other : users) {
        	if (other.getEmailId().equals(user.getEmailId()) && other.getUserPassword().equals(user.getUserPassword())) {            	
        		User us =  userRepository.getUserByEmailId(user.getEmailId()).orElseThrow(()->new UserNotFoundException("No User Found with this Username: "+user.getEmailId()));
            	if(!us.isLoggedIn())
            		return new ResponseEntity<String>("Already Logged out", HttpStatus.BAD_REQUEST);
            	us.setLoggedIn(false);
            	userService.updateUser(us);
            	return new ResponseEntity<String>("Logout Successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/byid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value="id") Long userId)
	{
		User user = userService.getUserById(userId).orElse(null);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/update/byid/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable(value="id") Long userId,@Valid @RequestBody User userinfo)
	{
		User user = userService.getUserById(userId).orElse(null);
		user.setEmailId(userinfo.getEmailId());
		user.setPhoneNumber(userinfo.getPhoneNumber());
		user.setUserName(userinfo.getUserName());
		user.setUserPassword(userinfo.getUserPassword());
		
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/byemail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable(value="email") String emailId)
	{
		User user = userService.getUserByEmailId(emailId);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/addRequest")
	public ResponseEntity<String> addRequest(@Valid @RequestBody AssistanceRequired assistanceRequired) {
		String addRequest = userService.sendRequest(assistanceRequired);
		if (addRequest == null) {
			return new ResponseEntity<String>("Service request not sent", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(addRequest, HttpStatus.OK);
	}
	
	@GetMapping("/searchMechanic/{loca}")
	public ResponseEntity<List<Mechanic>> searchMechanic(@PathVariable(value="loca") String location)
			throws MechanicNotFoundException {
		List<Mechanic> mechanicList = userService.searchMechanicByLocation(location);
		if (mechanicList.isEmpty()) {
			throw new MechanicNotFoundException("Mechanic does not exist at " + location + " location");
		}
		return new ResponseEntity<List<Mechanic>>(mechanicList, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}
	
	@PostMapping("/giveFeedback/{mechId}/{uId}")
	public ResponseEntity<String> giveNewFeedback(@Valid @PathVariable("mechId") long mechanicId,
			@PathVariable("uId") long userId, @RequestBody Feedback feedback) {

		String giveFeedback = userService.giveFeedback(feedback, mechanicId, userId);
		if (giveFeedback == null) {
			return new ResponseEntity<String>("Feedback not added", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(giveFeedback, HttpStatus.OK);
	}
}
