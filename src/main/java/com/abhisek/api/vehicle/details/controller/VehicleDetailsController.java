package com.abhisek.api.vehicle.details.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.errors.MandatoryFieldsMissingException;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailsController {

	@Autowired
	private VehicleDetailsService vehicleDetailsService;
	
	@GetMapping
	public String hello() {
		return "hello";
	}
	
	@PostMapping
	public ResponseEntity<VehicleDetails> saveVehicleDetails(@Valid@RequestBody VehicleDetails vehicleDetails, 
																BindingResult result) throws Exception{
		
		if(result.hasErrors()) {
			List<ObjectError> errorList=result.getAllErrors();
			
			String allErrors = "";
			for(ObjectError error: errorList) {
				allErrors += error.getDefaultMessage()+",";
			}
			throw new MandatoryFieldsMissingException(allErrors);
		}
		
		VehicleDetails dbVehicle = vehicleDetailsService.saveVehicleDetails(vehicleDetails);
		return new ResponseEntity<VehicleDetails>(dbVehicle,HttpStatus.CREATED);
	}
}
