package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Graphs {

	public static void passengerChange() throws Exception {
		int ITERATIONS = 30;

		String avgAddtion;
		String maxAddition;
		String avgWait;
		String midAddtion;

		double[] additionArr;
		double[] maxAdditionArr;
		double[] waitArr;
		double[] midAdditionArr;

		avgAddtion = maxAddition = avgWait = midAddtion = ",static method,naive method,dynamic method";

		for (int i = 10; i <= 150; i += 5) {
			additionArr = new double[3];
			maxAdditionArr = new double[3];
			waitArr = new double[3];
			midAdditionArr = new double[3];

			for (int j = 0; j < ITERATIONS; j++) {
				RequestsUpdate.NUM_OF_REQUESTS = i;
				//System.out.println("NUM_OF_REQUESTS: " + i + " num of drivers: " + Drive.NUM_OF_DRIVERS);
				//Drive.NUM_OF_DRIVERS = i / Driver.MAX_CAPACITY;
				DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
				DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(),
						d.getDrivers());
				DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(
						d.getRequestsList(), d.getDrivers());

				d.matchRequestsToDrivers();
				d.createRoute();

				d2.matchRequestsToDrivers();
				d2.createRoute();

				d3.matchRequestsToDrivers();
				d3.createRoute();

				Statistics statistics1 = Statistics.statistic(d.getDrivers(),
						"min update");
				Statistics statistics2 = Statistics.statistic(d2.getDrivers(),
						"min update");
				Statistics statistics3 = Statistics.statistic(d3.getDrivers(),
						"min update");

				additionArr[0] += statistics1.getAdditionPrecentage();
				additionArr[1] += statistics2.getAdditionPrecentage();
				additionArr[2] += statistics3.getAdditionPrecentage();

				for (double d1 : additionArr) {
					if (d1 == Double.NaN) {
						throw new Exception("drivers.size(): ");
					}
				}

				maxAdditionArr[0] += statistics1.getMaxAddition();
				maxAdditionArr[1] += statistics2.getMaxAddition();
				maxAdditionArr[2] += statistics3.getMaxAddition();

				waitArr[0] += statistics1.getWaitDistace();
				waitArr[1] += statistics2.getWaitDistace();
				waitArr[2] += statistics3.getWaitDistace();

				midAdditionArr[0] += statistics1.getMidAddition();
				midAdditionArr[1] += statistics2.getMidAddition();
				midAdditionArr[2] += statistics3.getMidAddition();

			}

			avgAddtion += "\n" + i + " passengers,"
					+ (additionArr[0] / ITERATIONS) + ","
					+ (additionArr[1] / ITERATIONS) + ","
					+ (additionArr[2] / ITERATIONS);

		/*  if (avgAddtion.contains("NaN")){
				for (double d1 : additionArr) {
					System.out.println(((Double)d1).isNaN());
				}
				throw new Exception("drivers.size(): ");
			} */

			maxAddition += "\n" + i + " passengers,"
					+ (maxAdditionArr[0] / ITERATIONS) + ","
					+ (maxAdditionArr[1] / ITERATIONS) + ","
					+ (maxAdditionArr[2] / ITERATIONS);

			avgWait += "\n" + i + " passengers," + (waitArr[0] / ITERATIONS)
					+ "," + (waitArr[1] / ITERATIONS) + ","
					+ (waitArr[2] / ITERATIONS);

			midAddtion += "\n" + i + " passengers,"
					+ (midAdditionArr[0] / ITERATIONS) + ","
					+ (midAdditionArr[1] / ITERATIONS) + ","
					+ (midAdditionArr[2] / ITERATIONS);

		}

		PrintWriter out;

		out = new PrintWriter("avgAddtion.csv");
		out.print(avgAddtion);
		out.close();

		out = new PrintWriter("maxAddition.csv");
		out.print(maxAddition);
		out.close();

		out = new PrintWriter("avgWait.csv");
		out.print(avgWait);
		out.close();

		out = new PrintWriter("midAddtion.csv");
		out.print(midAddtion);
		out.close();

		System.out.println("finish");

	}
}
