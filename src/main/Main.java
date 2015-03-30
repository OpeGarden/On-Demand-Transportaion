package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import main.Statistics;

public class Main {

	public static void main(String[] args) {
		DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
		
		DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
		
		DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
		
	
		
		main1(d);
		main2(d2);
		main3(d3);

	}

	private static void main3(Drive d){
		 
				
		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "min update");
		DrawDrive.draw("min update", d);

	}
	private static void main2(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "old");
		DrawDrive.draw("old", d);

	}

	private static void main1(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "array method");
		DrawDrive.draw("array method", d);
	}

}
