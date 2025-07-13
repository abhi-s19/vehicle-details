package com.abhisek.api.vehicle.details.errors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class VehicleDetailsExceptionHandler {

	@ExceptionHandler(MandatoryFieldsMissingException.class)
	public ResponseEntity<ErrorResponse> handleMandatoryFieldsMissingException(MandatoryFieldsMissingException ex){
		
		List<String> errors = Arrays.stream(ex.getMessage().split(",")).collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,errors);
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(VehicleNotSavedException.class)
	public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(VehicleNotSavedException ex){
		
		List<String> errors = Arrays.stream(ex.getMessage().split(",")).collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.EXPECTATION_FAILED, errors);
		
		return new ResponseEntity<>(errorResponse,HttpStatus.EXPECTATION_FAILED);
	}
}
