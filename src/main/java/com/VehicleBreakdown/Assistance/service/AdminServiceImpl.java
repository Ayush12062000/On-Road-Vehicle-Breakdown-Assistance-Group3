package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.Admin;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AdminRepository;
import com.VehicleBreakdown.Assistance.repository.FeedbackRepository;
import com.VehicleBreakdown.Assistance.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Optional<Admin> getAdminByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public List<Feedback> viewFeedback() {
		return feedbackRepository.findAll();
	}
}
