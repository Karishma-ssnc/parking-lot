package com.ezest.parkinglot.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.parkinglot.app.model.VehicleDetails;
import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;

public class ParkingServiceTest {
	
	private ParkingService parkingService = new ParkingServiceImpl() ;
	
	@Test
	public void testParkVehicle() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		String expectedValue = parkingService.parkVehicle(vehicleDetails);
		String actualValue = "Vehicle is parked on slot 1";
		assertEquals(expectedValue, actualValue);
	}
	
	
	@Test
	public void testLeaveVehicle() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		parkingService.parkVehicle(vehicleDetails);
		String actualValue = "Slot 1 is free";
		String expectedValue = parkingService.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testParkingReceipt() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		Double expectedValue = parkingService.generateParkingReceipt(vehicleDetails);
		Double actualValue = 30.0;
		assertEquals(expectedValue, actualValue);
		
	}
	
	
	
}
