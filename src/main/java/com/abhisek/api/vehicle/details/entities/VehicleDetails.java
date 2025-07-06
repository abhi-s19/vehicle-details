package com.abhisek.api.vehicle.details.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "model_year")
	private int modelYear;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "model_name")
	private String modelName;
	
	@Column(name = "trim_type")
	private String trimType;
	
	@Column(name = "body_type")
	private String bodyType;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "miles")
	private int miles;
	
	@Column(name = "interest_rate")
	private double interestRate;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "vehicle_description")
	private String vehicleDescription;
	
	@Column(name = "seller_name")
	private String sellerName;
	
	@Column(name = "seller_phone")
	private String sellerPhone;
	
}
