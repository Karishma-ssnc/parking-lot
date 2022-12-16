package com.ezest.parkinglot.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;


@SpringBootTest
class ParkingLotApplicationTests {
	private ParkingService parkingService = new ParkingServiceImpl() ;

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}
}
