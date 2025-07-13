package com.abhisek.api.vehicle.details.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhisek.api.vehicle.details.entities.VehicleDetails;

public interface VehicleDetailsDAO extends JpaRepository<VehicleDetails, Integer> {

}
