package com.parkinglot.app.service;

import com.parkinglot.app.model.VehicleDetails;

public interface ParkingService {

	public String parkVehicle(VehicleDetails vehicle);

	public VehicleDetails leaveVehicle(Integer slotNumber);

	
}
