package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AssistanceRequiredRepository;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;
import com.VehicleBreakdown.Assistance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	MechanicRepository mechanicRepository;
	
	@Autowired
	AssistanceRequiredRepository assistanceRepository;

	@Override
	public User userRegistration(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User getUserByEmailId(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	@Override
	public String sendRequest(AssistanceRequired assistanceRequired) {
		boolean userinfo = userRepository.existsById(assistanceRequired.getUserId());
		if(userinfo==false)
        {
        	return "User does not exists. Please register first";
        }
		boolean mechanicinfo = mechanicRepository.existsById(assistanceRequired.getMechanicId());
		if(mechanicinfo==false)
        {
        	return "Mechanic does not exists. Please register first";
        }
		
		assistanceRepository.save(assistanceRequired);
		return "Service Requested Successfully";
	}

	@Override
	public List<Mechanic> searchMechanicByLocation(String location) {
		return mechanicRepository.findByMechanicLocation(location);
	}

	/*@Override
	public AssistanceRequired checkServiceExist(long userId) {
		return assistanceRepository.getByUserId(userId);
	}*/
	
	
}
