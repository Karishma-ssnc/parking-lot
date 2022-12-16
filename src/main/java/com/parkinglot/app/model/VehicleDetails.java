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
	
	public VehicleDetails(Integer vehicalId, String vehicalType, String vehicalRegNo, String color,
			String parkingStatus, LocalDateTime parkedTime, String ticketNumber) {
		
		this.vehicalId = vehicalId;
		this.vehicalType = vehicalType;
		this.vehicalRegNo = vehicalRegNo;
		this.color = color;
		this.parkingStatus = parkingStatus;
		this.parkedTime = parkedTime;
		this.ticketNumber = ticketNumber;
	}
	
	
	
	
	
	
	
}
