package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Drive;
import main.Driver;
import main.TransportRequest;

import org.junit.Test;

public class DriveTest {
	
	
	@Test
	public void isSameDuplicatedPassenger() {
		
		Drive drive = new Drive();
		drive.matchRequestsToDrivers();
		ArrayList<Driver> drivers = drive.getDrivers();
		for (int i = 0; i < drivers.size(); i++) {
			Driver driver = drivers.get(i);
			for(TransportRequest passenger : driver.getPassengers()){
				for (int j = 0; j < drivers.size(); j++) {
					if(i==j) continue;
					Driver otherDriver = drivers.get(j);
					if(otherDriver.getPassengers().contains(passenger)){
						fail("duplicate passenger");
					}
				
				}
				
			}
		}

	}

}
