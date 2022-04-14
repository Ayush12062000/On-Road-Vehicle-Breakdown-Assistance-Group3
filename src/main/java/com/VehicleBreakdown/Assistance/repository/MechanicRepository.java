package com.VehicleBreakdown.Assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VehicleBreakdown.Assistance.model.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long>  {

}
