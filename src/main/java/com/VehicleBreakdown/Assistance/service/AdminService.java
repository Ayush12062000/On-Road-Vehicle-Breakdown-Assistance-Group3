package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import com.VehicleBreakdown.Assistance.model.Admin;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;


public interface AdminService {
	public List<User> getAllUsers();
	public Admin updateAdmin(Admin admin);
	public Optional<Admin> getAdminByUsername(String username);
	public List<Feedback> viewFeedback();
	public List<Mechanic> getAllMechanics();
	public String allowOrBlockMechanic(long mechanicId);
}
