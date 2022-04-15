package com.VehicleBreakdown.Assistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	public boolean findByUserId(long userId);
	public List<Feedback> findByMechanic(Mechanic m);
	public Feedback findByUserIdAndMechanic(long userId, Mechanic m);
}
