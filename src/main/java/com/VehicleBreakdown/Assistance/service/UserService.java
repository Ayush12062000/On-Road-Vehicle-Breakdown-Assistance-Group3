package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;


public interface UserService {
	public User userRegistration(User user);
	public List<User> getAllUsers();
	public User updateUser(User user);
	public Optional<User> getUserById(Long userId);
	public User getUserByEmailId(String emailId);
	public String sendRequest(AssistanceRequired assistanceRequired);
	public List<Mechanic> searchMechanicByLocation(String location);
	//public AssistanceRequired checkServiceExist(long userId);
	public String giveFeedback(Feedback feedback,long mechanicId,long userId);
}
