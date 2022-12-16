package com.parkinglot.app.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VehicleDetails {
	private Integer vehicalId;
	private  String vehicalType;
	private String vehicalRegNo;
	private String color;
	private String parkingStatus;
	private LocalDateTime parkedTime;
	private String ticketNumber;
	private Double parkingCharges;
	
}
