package com.parkinglot.app.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.app.model.VehicleDetails;
import com.parkinglot.app.service.ParkingService;

@RequestMapping("/parking")
@RestController
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
		
	@GetMapping("/parking-size")
	public ResponseEntity<?> getParkingSlots(@RequestParam("parking-size") String parkingSize) {
		return new ResponseEntity<>(parkingService.createParkingSlots(Integer.parseInt(parkingSize)), HttpStatus.OK);
	}
	
	@PostMapping("/park-vehicle")
	public ResponseEntity<?> registerVehicle(@RequestBody VehicleDetails vehicle) {
		 Random rnd = new Random();
		 int number = rnd.nextInt(999999);
		 //vehicle.setTicketNumber(String.format("%06d", number));
		 //vehicle.setParkedTime(LocalDateTime.now());
		 return new ResponseEntity<>(parkingService.parkVehicle(vehicle), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/leave-vehicle")
	public ResponseEntity<?> leaveVehicle(@RequestParam("slot-number") String slotNumber){
		return new ResponseEntity<>(parkingService.leaveVehicle(Integer.parseInt(slotNumber)), HttpStatus.OK);
		
	}
}
