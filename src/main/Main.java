package main;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import main.Statistics;

public class Main {

	public static void main(String[] args) {
		

		maingraph();
		
		
		

	}
	public static void main0(){
		DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
		DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
		DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
		main1(d);
		main2(d2);
		main3(d3);
	}
	public static void maingraph(){
		try {
			Graphs.passengerChange();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main3(Drive d){
		 
				
		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "dynamic");
		DrawDrive.draw("min update", d);

	}
	public static void main2(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "naive");
		DrawDrive.draw("old", d);

	}

	public static void main1(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers(), "static");
		DrawDrive.draw("array method", d);
	}

}
