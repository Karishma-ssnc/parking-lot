package com.ezest.parkinglot.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkinglot.app.controller.ParkingController;
import com.parkinglot.app.model.VehicleDetails;
import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;



public class ParkingControllerTest {
	
	@InjectMocks
    private ParkingController parkingController;

	
	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	ParkingService mock = org.mockito.Mockito.mock(ParkingService.class);

	@Test
	public void testParkVehicle() {
		VehicleDetails vehicleDetails = new VehicleDetails(1, "CAR", "1234", "BLACK", "Not Parked", null, "12345");
		Mockito.when(mock.parkVehicle(vehicleDetails)).thenReturn("Vehicle is parked on slot 1");

		/*
		 * String json = mapper.writeValueAsString(vehicleDetails);
		 * mockMvc.perform(post("/park-vehicle").contentType(MediaType.APPLICATION_JSON)
		 * .characterEncoding("utf-8")
		 * .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().
		 * isCreated()) .andExpect(jsonPath("$.vehicalId", Matchers.equalTo(1)))
		 * .andExpect(jsonPath("$.vehicalType", Matchers.equalTo("CAR")));
		 */

	}

	@Test
	public void testLeaveVehicle() {
		VehicleDetails vehicleDetails = new VehicleDetails(1, "CAR", "1234", "RED", "Not Parked", LocalDateTime.now(),
				"12345");
		mock.createParkingSlots(1);
		mock.parkVehicle(vehicleDetails);
		Mockito.when(mock.parkVehicle(vehicleDetails)).thenReturn("Slot 1 is free");
	}
}
