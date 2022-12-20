package com.parkinglot.app.service;

import com.parkinglot.app.model.VehicleDetails;

public interface ParkingService {

	public String parkVehicle(VehicleDetails vehicle);

	public String leaveVehicle(Integer slotNumber);
	
	public Double calculateParkingCharges(VehicleDetails vehicleDetails);

	public void createParking();

	
}
