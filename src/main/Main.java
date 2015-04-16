package main;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import main.Statistics;

public class Main {

	public static void main(String[] args) {
		
		try {
			Graphs.passengerChange();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
		
		DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
		
		DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
		
	
		
		main1(d);
		main2(d2);
		main3(d3);*/ catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main3(Drive d, boolean graph){
		 
				
		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "min update");
		if(graph) DrawDrive.draw("min update", d);

	}
	public static void main2(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "old");
		DrawDrive.draw("old", d);

	}

	public static void main1(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "array method");
		DrawDrive.draw("array method", d);
	}

}
