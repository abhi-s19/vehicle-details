package com.abhisek.api.vehicle.details.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailsController {

	@Autowired
	private VehicleDetailsService vehicleDetailsService;
	
	@PostMapping
	public ResponseEntity<VehicleDetails> saveVehicleDetails(@RequestBody VehicleDetails vehicleDetails){
		VehicleDetails dbVehicle = vehicleDetailsService.saveVehicleDetails(vehicleDetails);
		return new ResponseEntity<VehicleDetails>(dbVehicle,HttpStatus.CREATED);
	}
}
