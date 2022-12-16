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
	@Value("${parking.size}")
	private Integer parkingSize;
	private Integer bikeCharges = 30;
	private Integer busCharges = 100;
	
	
	@Override
	public String parkVehicle(VehicleDetails vehicle) {
		System.out.println("Parking Size : "+parkingSize);
		for (int i = 0; i < parkingSize; i++) {
			parkingDetailsMap.put(i+1, null);
			availableParkingSlots.add(i+1);
		}
		if(availableParkingSlots.isEmpty()) {
			return "Sorry, parking slot is full";
		}else {
			final int emptyParking = availableParkingSlots.pollFirst();
			System.out.println("Available Parking slots : "+emptyParking);
			parkingDetailsMap.put(emptyParking, vehicle);
			return "Vehicle is parked on slot "+ emptyParking;
		}
	}

	public VehicleDetails leaveVehicle(Integer slotNumber){
		for (Map.Entry<Integer, VehicleDetails> entry : parkingDetailsMap.entrySet()) {
			if(entry.getKey() == slotNumber) {
				VehicleDetails vehicleDetails = entry.getValue();
				Double parkingCharges = generateParkingReceipt(vehicleDetails);
				parkingDetailsMap.put(entry.getKey(), null);
				availableParkingSlots.add(slotNumber);
				return vehicleDetails;
			}
		}
		return null;
	}
	
	public Double generateParkingReceipt(VehicleDetails vehicleDetails) {
		Duration totalParkedDuration = Duration.between(LocalDateTime.now(),vehicleDetails.getParkedTime());
		Double parkingCharges = 0.0;
		if(vehicleDetails.getVehicalType().equalsIgnoreCase("CAR") || vehicleDetails.getVehicalType().equalsIgnoreCase("BIKE")) {
			parkingCharges = (double) (bikeCharges + (10 * totalParkedDuration.toHours()));
		} else if(vehicleDetails.getVehicalType().equalsIgnoreCase("BUS") || vehicleDetails.getVehicalType().equalsIgnoreCase("TRUCK")) {
			parkingCharges = (double) (busCharges + (30 * totalParkedDuration.toHours()));
		}
		return parkingCharges;
	}
	
}
