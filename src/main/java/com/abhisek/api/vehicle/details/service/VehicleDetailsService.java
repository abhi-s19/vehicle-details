package com.abhisek.api.vehicle.details.service;

import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.errors.VehicleNotSavedException;

public interface VehicleDetailsService {
	
	VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails) throws VehicleNotSavedException;

}
