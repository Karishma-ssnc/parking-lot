package com.parkinglot.app.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Parking {
	private Integer parkingId;
	private Integer parkingSize;
	private Integer laneNumber;
	private Integer slot;
	private Double totalCharges;
	private Integer vehicleId;
}
 