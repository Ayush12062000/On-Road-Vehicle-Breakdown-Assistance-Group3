package com.VehicleBreakdown.Assistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VehicleBreakdown.Assistance.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findByUsername(String username);
}
