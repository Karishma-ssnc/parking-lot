package com.parkinglot.app.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.parkinglot.app.model.VehicleDetails;

@Service
@PropertySource(value={"classpath:application.properties"})
public class ParkingServiceImpl implements ParkingService{

	private static Map<Integer, VehicleDetails> parkingDetailsMap = new HashMap<>();
	private static TreeSet<Integer> availableParkingSlots = new TreeSet<>();
	//@Value("${parking.size}")
	//private Integer parkingSize = 1;
	private Integer bikeCharges = 30;
	private Integer busCharges = 100;
	
	public String createParkingSlots(Integer parkingSize) {
		System.out.println("Parking Size : "+parkingSize);
		for (int i = 0; i < parkingSize; i++) {
			parkingDetailsMap.put(i+1, null);
			availableParkingSlots.add(i+1);
		}
		return parkingSize +" Slots are created";
	}
	@Override
	public String parkVehicle(VehicleDetails vehicle) {
		if(availableParkingSlots.isEmpty()) {
			return "Sorry, parking is full";
		}else {
			final int emptyParking = availableParkingSlots.pollFirst();
			parkingDetailsMap.put(emptyParking, vehicle);
			return "Vehicle is parked on slot "+ emptyParking;
		}
	}

	public String leaveVehicle(Integer slotNumber){
		for (Map.Entry<Integer, VehicleDetails> entry : parkingDetailsMap.entrySet()) {
			if(entry.getKey() == slotNumber) {
				VehicleDetails vehicleDetails = entry.getValue();
				Double parkingCharges = calculateParkingCharges(vehicleDetails);
				vehicleDetails.setParkingCharges(parkingCharges);
				parkingDetailsMap.put(entry.getKey(), null);
				availableParkingSlots.add(slotNumber);
				return "Slot 1 is free";
			}
		}
		return "Slot is not available";
	}
	
	public Double calculateParkingCharges(VehicleDetails vehicleDetails) {
		Duration totalParkedDuration = Duration.between(LocalDateTime.now(),vehicleDetails.getParkedTime());
		Double parkingCharges = 0.0;
		if(vehicleDetails.getVehicalType().equalsIgnoreCase("CAR") || vehicleDetails.getVehicalType().equalsIgnoreCase("BIKE")) {
			parkingCharges = (double) (bikeCharges + (10 * totalParkedDuration.toHours()));
			System.out.println("parkingCharges : "+parkingCharges);
		} else if(vehicleDetails.getVehicalType().equalsIgnoreCase("BUS") || vehicleDetails.getVehicalType().equalsIgnoreCase("TRUCK")) {
			parkingCharges = (double) (busCharges + (30 * totalParkedDuration.toHours()));
		}
		return parkingCharges;
	}
	
}
