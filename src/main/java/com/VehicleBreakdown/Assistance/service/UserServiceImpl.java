package com.VehicleBreakdown.Assistance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AssistanceRequiredRepository;
import com.VehicleBreakdown.Assistance.repository.FeedbackRepository;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;
import com.VehicleBreakdown.Assistance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired
	private AssistanceRequiredRepository assistanceRequiredRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;

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
		
		assistanceRequiredRepository.save(assistanceRequired);
		return "Service Requested Successfully";
	}

	@Override
	public List<Mechanic> searchMechanicByLocation(String location) {
		return mechanicRepository.findByMechanicLocation(location);
	}

	@Override
	public String giveFeedback(Feedback feedback, long mechanicId, long userId) {
		Mechanic mechanic=mechanicRepository.getById(mechanicId);
		AssistanceRequired ar = assistanceRequiredRepository.findByUserIdAndMechanicId(userId,mechanicId);
		
		if(ar== null)
		{
			return "Service for given user and mechanic doesnt exist";
		}
		
		Feedback feed= feedbackRepository.findByUserIdAndMechanic(userId,mechanic);
		if(feed!=null) {
			return "Feedback Already Exists"; 
		}
		
		Feedback feedback1=new Feedback();
		feedback1.setUserId(userId);
		feedback1.setFeedbackMessage(feedback.getFeedbackMessage());
		feedback1.setRatings(feedback.getRatings());
		feedback1.setMechanic(mechanic);
		feedback1.setAssiatnceRequired(ar);
		feedbackRepository.save(feedback1);
		return "Feedback Added";
	}

}
