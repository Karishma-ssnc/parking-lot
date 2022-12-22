package com.parkinglot.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkinglot.app.controller.ParkingController;
import com.parkinglot.app.model.VehicleDetails;
import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;

@WebMvcTest(ParkingController.class)
public class ParkingControllerTest {

	@InjectMocks
	private ParkingController parkingController;

	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	@MockBean
	private ParkingService parkingService;

	@Test
	public void testCreateParkingSlots() throws Exception {
		Mockito.when(parkingService.createParkingSlots(1)).thenReturn("1 Slot is created");
		MvcResult requestResult = mockMvc.perform(get("/parking/parking-size").param("parking-size", "1"))
				.andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals(result, "1 Slot is created");
	}

	@Test
	public void testParkVehicle() throws Exception {
		VehicleDetails vehicleDetails = new VehicleDetails(1, "CAR", "1234", "BLACK", "Not Parked", null, "12345");
		Mockito.when(parkingService.parkVehicle(vehicleDetails)).thenReturn("Vehicle is parked on slot 1");

		String json = mapper.writeValueAsString(vehicleDetails);
		// System.out.println("json : "+json.toCharArray());
		mockMvc.perform(post("/parking/park-vehicle").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		// .andExpect(jsonPath("$.vehicalId", Matchers.equalTo(1)))
		// .andExpect(jsonPath("$.vehicalType", Matchers.equalTo("CAR")));

	}

	@Test
	public void testLeaveVehicle() throws Exception {
		VehicleDetails vehicleDetails = new VehicleDetails(1, "CAR", "1234", "RED", "Not Parked", null, "12345");
		parkingService.createParkingSlots(1);
		parkingService.parkVehicle(vehicleDetails);
		Mockito.when(parkingService.leaveVehicle(1)).thenReturn("Slot 1 is free");
		MvcResult requestResult = mockMvc.perform(delete("/parking/leave-vehicle").param("slot-number", "1"))
				.andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals(result, "Slot 1 is free");
	}
}
