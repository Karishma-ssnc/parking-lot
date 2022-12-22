package com.ezest.parkinglot.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.parkinglot.app.model.VehicleDetails;
import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;

public class ParkingServiceTest {
	
	private ParkingService parkingService = new ParkingServiceImpl() ;
	
	@Test
	@Order(1)    
	public void testParkVehicle() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		parkingService.createParkingSlots(1);
		String expectedValue = parkingService.parkVehicle(vehicleDetails);
		String actualValue = "Vehicle is parked on slot 1";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	@Order(2)    
	public void testIfSlotIsNotAvailableToPark() {
		Map<Integer, VehicleDetails> parkingDetailsMap = new HashMap<>();
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","BLACK","Not Parked",LocalDateTime.now(),"12345");
		VehicleDetails vehicleDetails1 = new VehicleDetails(2,"BUS","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		parkingService.createParkingSlots(1);
		parkingDetailsMap.put(1, vehicleDetails);
		parkingDetailsMap.put(2, vehicleDetails1);
		parkingService.parkVehicle(vehicleDetails);
		String expectedValue = parkingService.parkVehicle(vehicleDetails1);
		String actualValue = "Sorry, parking is full";
		assertEquals(expectedValue, actualValue);
	}
	
	
	@Test
	@Order(3)    
	public void testLeaveVehicleIfSlotAvailable() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		parkingService.createParkingSlots(1);
		parkingService.parkVehicle(vehicleDetails);
		String actualValue = "Slot 1 is free";
		String expectedValue = parkingService.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	@Order(4)    
	public void testLeaveVehicleIfSlotNotAvailable() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		parkingService.createParkingSlots(1);
		parkingService.parkVehicle(vehicleDetails);
		String actualValue = "Slot is not available";
		String expectedValue = parkingService.leaveVehicle(5);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	@Order(5)    
	public void testCalculateParkingChargesForCar() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"CAR","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		Double expectedValue = parkingService.calculateParkingCharges(vehicleDetails);
		Double actualValue = 30.0;
		assertEquals(expectedValue, actualValue);
		
	}
	
	@Test
	@Order(6)    
	public void testCalculateParkingChargesForBus() {
		VehicleDetails vehicleDetails = new VehicleDetails(1,"Bus","1234","RED","Not Parked",LocalDateTime.now(),"12345");
		Double expectedValue = parkingService.calculateParkingCharges(vehicleDetails);
		Double actualValue = 100.0;
		assertEquals(expectedValue, actualValue);
		
	}
	
	
}