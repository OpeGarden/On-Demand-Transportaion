package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import main.Statistics;

public class Main {

	public static void main(String[] args) {
		DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
		
		DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
		
		
		main1(d);
		main2(d2);

	}

	private static void main2(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers());
		DrawDrive.draw("absMIn", d, 100, 100);

	}

	private static void main1(Drive d) {

		d.matchRequestsToDrivers();

		d.createRoute();

		Statistics.statistic(d.getDrivers());
		DrawDrive.draw("old", d, 100, 100);
	}

}
