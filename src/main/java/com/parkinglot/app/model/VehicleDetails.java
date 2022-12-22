package com.parkinglot.app.model;

import java.time.LocalDateTime;

import lombok.Data;

//@Data
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
		super();
		this.vehicalId = vehicalId;
		this.vehicalType = vehicalType;
		this.vehicalRegNo = vehicalRegNo;
		this.color = color;
		this.parkingStatus = parkingStatus;
		this.parkedTime = parkedTime;
		this.ticketNumber = ticketNumber;
		
	}


	public String getVehicalType() {
		return vehicalType;
	}


	/*
	 * public void setVehicalType(String vehicalType) { this.vehicalType =
	 * vehicalType; }
	 */


	public LocalDateTime getParkedTime() {
		return parkedTime;
	}


	/*
	 * public void setParkedTime(LocalDateTime parkedTime) { this.parkedTime =
	 * parkedTime; }
	 */


	/*
	 * public Double getParkingCharges() { return parkingCharges; }
	 */


	public void setParkingCharges(Double parkingCharges) {
		this.parkingCharges = parkingCharges;
	}


	/*
	 * public String getTicketNumber() { return ticketNumber; }
	 * 
	 * 
	 * public void setTicketNumber(String ticketNumber) { this.ticketNumber =
	 * ticketNumber; }
	 */
	
	
	
	
	
}
