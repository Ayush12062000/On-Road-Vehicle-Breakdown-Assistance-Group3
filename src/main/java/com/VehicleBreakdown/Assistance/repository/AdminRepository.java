package com.VehicleBreakdown.Assistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VehicleBreakdown.Assistance.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findByUsername(String username);
}
