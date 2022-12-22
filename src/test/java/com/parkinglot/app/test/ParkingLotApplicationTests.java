package com.parkinglot.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.parkinglot.app.ParkingLotApplication;
import com.parkinglot.app.service.ParkingService;
import com.parkinglot.app.service.ParkingServiceImpl;


@SpringBootTest
class ParkingLotApplicationTests {
	 @Test
	 public void testApplicationContextLoaded(){
	 }

	 @Test
	 public void testAapplicationStart() {
		 ParkingLotApplication.main(new String[] {});
	 }
}
