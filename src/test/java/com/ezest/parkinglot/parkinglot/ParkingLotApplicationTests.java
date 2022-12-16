package com.ezest.parkinglot.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingLotApplicationTests {

	 @BeforeAll  
	  public static void init() {  
	        System.out.println("Parking Lot Testing");  
	  }  
	  @Test  
	  public void test() {  
	        String expectedString = "Success";  
			assertEquals(expectedString, "Success");  
		    System.out.println("Success!");  
	  } 
	  
	  @Test  
	  public void testMessage() {  
	        String expectedString = "Success";  
			assertEquals(expectedString, "Success");  
		    System.out.println("Test Message!");  
	  }
}
