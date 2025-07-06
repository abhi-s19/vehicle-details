package com.abhisek.api.vehicle.details.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhisek.api.vehicle.details.dao.VehicleDetailsDAO;
import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;


@Service
public class VehicleDetailsServiceImpl implements VehicleDetailsService {
	
	@Autowired
	private VehicleDetailsDAO vehicleDetailsDAO;

	@Override
	public VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails) {
		// TODO Auto-generated method stub
		return vehicleDetailsDAO.save(vehicleDetails);
	}

}
