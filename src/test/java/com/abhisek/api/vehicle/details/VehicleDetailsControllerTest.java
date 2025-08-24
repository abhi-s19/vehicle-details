package com.abhisek.api.vehicle.details;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abhisek.api.vehicle.details.controller.VehicleDetailsController;
import com.abhisek.api.vehicle.details.entities.VehicleDetails;
import com.abhisek.api.vehicle.details.service.VehicleDetailsService;


@WebMvcTest(VehicleDetailsController.class)
class VehicleDetailsControllerTest {
	
	@MockBean
	VehicleDetailsService vehicleDetailsService;
	
	@Autowired
	MockMvc mockMvc;
	
	private VehicleDetails output;
	

	@BeforeEach
	void setUpBeforeClass(){
		
		output = new VehicleDetails();
		output.setId(1);
		output.setModelYear(2025);
		output.setBrandName("honda");
		output.setModelName("Accord");
		output.setTrimType("Ls");
		output.setBodyType("coupe");
		output.setMiles(1200);
		output.setPrice(2500000);
		output.setInterestRate(7.5);
		output.setLocation("Kol");
		output.setVehicleDescription("very good");
		output.setSellerName("Amazing auto");
		output.setSellerPhone("111");
		
	}

	@Test
	@DisplayName("Test save vehicle details controller endpoint")
	void testSaveVehicleDetails() throws Exception {
		
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
		
		Mockito.when(vehicleDetailsService.saveVehicleDetails(input)).thenReturn(output);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/vehicle-details")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n"
								+ "  \"modelYear\": 2025,\r\n"
								+ "  \"brandName\": \"honda\",\r\n"
								+ "  \"modelName\": \"Accord\",\r\n"
								+ "  \"trimType\": \"Ls\",\r\n"
								+ "  \"bodyType\": \"coupe\",\r\n"
								+ "  \"price\": 2500000,\r\n"
								+ "  \"miles\": 1200,\r\n"
								+ "  \"interestRate\": 7.5,\r\n"
								+ "  \"location\": \"Kol\",\r\n"
								+ "  \"vehicleDescription\": \"very good\",\r\n"
								+ "  \"sellerName\": \"Amazing auto\",\r\n"
								+ "  \"sellerPhone\": \"111\"\r\n"
								+ "}")).andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@Test
	void testGetAllVehicleDetailsController() throws Exception {
		List<VehicleDetails> listOutput = Arrays.asList(
				new VehicleDetails(1,2011,"tata","tata1","tata2","tata3",1.4,1,1.5,"kol","good","abhi","123456"),
				new VehicleDetails(2,2012,"kia","kia1","kia2","kia3",1.4,1,1.5,"kol","good","abhi","123456")
				);
		Mockito.when(vehicleDetailsService.fetchAllVehicleDetails()).thenReturn(listOutput);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vehicle-details").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].brandName").value("tata"));
	}

}
