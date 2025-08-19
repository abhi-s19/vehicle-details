package com.abhisek.api.vehicle.details;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.abhisek.api.vehicle.details.dao.VehicleDetailsDAO;
import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.errors.VehicleNotSavedException;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;

@SpringBootTest
class VehicleDetailsServiceTest {
	
	@MockBean
	private VehicleDetailsDAO vehicleDetailsDAO;
	
	private VehicleDetails vehicleDetails;
	
	@Autowired
	private VehicleDetailsService vehicleDetailsService;
	

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		
		vehicleDetails = new VehicleDetails();
		vehicleDetails.setId(1);
		vehicleDetails.setModelYear(2025);
		vehicleDetails.setBrandName("honda");
		vehicleDetails.setModelName("Accord");
		vehicleDetails.setTrimType("Ls");
		vehicleDetails.setBodyType("coupe");
		vehicleDetails.setMiles(1200);
		vehicleDetails.setPrice(2500000);
		vehicleDetails.setInterestRate(7.5);
		vehicleDetails.setLocation("Kol");
		vehicleDetails.setVehicleDescription("very good");
		vehicleDetails.setSellerName("Amazing auto");
		vehicleDetails.setSellerPhone("111");
		
		
	}

	@Test
	@DisplayName("Test vehicle details saved when passed valid input data")
	void testSaveVehicleDetails() throws VehicleNotSavedException {
		
		VehicleDetails input = new VehicleDetails();
		
		input.setModelYear(2025);
		input.setBrandName("honda");
		input.setModelName("Accord");
		input.setTrimType("Ls");
		input.setBodyType("coupe");
		input.setMiles(1200);
		input.setPrice(2500000);
		input.setInterestRate(7.5);
		input.setLocation("Kol");
		input.setVehicleDescription("very good");
		input.setSellerName("Amazing auto");
		input.setSellerPhone("111");
		
		Mockito.when(vehicleDetailsDAO.save(input)).thenReturn(vehicleDetails);
		
		VehicleDetails output = vehicleDetailsService.saveVehicleDetails(input);
		assertEquals(output.getId(), vehicleDetails.getId());
		assertEquals(output.getBrandName(), vehicleDetails.getBrandName());
		assertEquals(output.getSellerName(), vehicleDetails.getSellerName());
	}

}
