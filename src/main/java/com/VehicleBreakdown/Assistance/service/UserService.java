package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import com.VehicleBreakdown.Assistance.model.User;


public interface UserService {
	public User userRegistration(User user);
	public List<User> getAllUsers();
	public User updateUser(User user);
	public Optional<User> getUserById(Long userId);
	public User getUserByEmailId(String emailId);
}
