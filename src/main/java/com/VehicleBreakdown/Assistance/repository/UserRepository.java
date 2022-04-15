package com.VehicleBreakdown.Assistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.VehicleBreakdown.Assistance.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailId(String emailId);
	Optional<User> getUserByEmailId(String emailId);
}
