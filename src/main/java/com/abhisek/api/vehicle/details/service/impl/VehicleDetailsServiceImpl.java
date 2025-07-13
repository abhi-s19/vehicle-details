package com.abhisek.api.vehicle.details.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhisek.api.vehicle.details.dao.VehicleDetailsDAO;
import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.errors.VehicleNotSavedException;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;


@Service
public class VehicleDetailsServiceImpl implements VehicleDetailsService {
	
	@Autowired
	private VehicleDetailsDAO vehicleDetailsDAO;

	@Override
	public VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails) throws VehicleNotSavedException {
		VehicleDetails dbVehicle = null;
		
		try {
			dbVehicle = vehicleDetailsDAO.save(vehicleDetails);
		}
		catch(Exception ex) {
			throw new VehicleNotSavedException("Unable to save vehicle in DB. Got Error:- "+ex.getMessage());
		}
		return dbVehicle;
	}

}
