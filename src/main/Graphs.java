package main;


import java.io.PrintWriter;

public class Graphs {
	private static int ITERATIONS;
	private static int PASSENGERS;
	
	public Graphs(int runs, int numPassenger) {
		ITERATIONS = runs;
		PASSENGERS = numPassenger;
	}

	public static void passengerChange(boolean areas) throws Exception {

		String avgAddtion;
		String maxAddition;
		String avgWait;
		String midAddtion;

		double[] additionArr;
		double[] maxAdditionArr;
		double[] waitArr;
		double[] midAdditionArr;

		avgAddtion = maxAddition = avgWait = midAddtion = ",static method,naive method,dynamic method,one passenger method";

		for (int i = 10; i <= PASSENGERS; i += 5) {
			additionArr = new double[4];
			maxAdditionArr = new double[4];
			waitArr = new double[4];
			midAdditionArr = new double[4];

			for (int j = 0; j < ITERATIONS; j++) {
				RequestsUpdate.NUM_OF_REQUESTS = i;

				Drive.NUM_OF_DRIVERS = i / Driver.MAX_CAPACITY;
				
				DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA(areas);
				DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
				DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
				DriveOndPassengerAtTime d4 = new DriveOndPassengerAtTime(d.getRequestsList(), d.getDrivers());

				d.matchRequestsToDrivers();
				d.createRoute();

				d2.matchRequestsToDrivers();
				d2.createRoute();

				d3.matchRequestsToDrivers();
				d3.createRoute();
				
				d4.matchRequestsToDrivers();
				d4.createRoute();

				Statistics statistics1 = Statistics.statistic(d.getDrivers(),
						"Static");
				Statistics statistics2 = Statistics.statistic(d2.getDrivers(),
						"Naive");
				Statistics statistics3 = Statistics.statistic(d3.getDrivers(),
						"Dynamic");
				Statistics statistics4 = Statistics.statistic(d4.getDrivers(),
						"one passenger");

				additionArr[0] += statistics1.getAdditionPrecentage();
				additionArr[1] += statistics2.getAdditionPrecentage();
				additionArr[2] += statistics3.getAdditionPrecentage();
				additionArr[3] += statistics4.getAdditionPrecentage();

				for (double d1 : additionArr) {
					if (d1 == Double.NaN) {
						throw new Exception("drivers.size(): ");
					}
				}

				maxAdditionArr[0] += statistics1.getMaxAddition();
				maxAdditionArr[1] += statistics2.getMaxAddition();
				maxAdditionArr[2] += statistics3.getMaxAddition();
				maxAdditionArr[3] += statistics4.getMaxAddition();

				waitArr[0] += statistics1.getWaitDistace();
				waitArr[1] += statistics2.getWaitDistace();
				waitArr[2] += statistics3.getWaitDistace();
				waitArr[3] += statistics4.getWaitDistace();

				midAdditionArr[0] += statistics1.getMidAddition();
				midAdditionArr[1] += statistics2.getMidAddition();
				midAdditionArr[2] += statistics3.getMidAddition();
				midAdditionArr[3] += statistics4.getMidAddition();

			}

			avgAddtion += "\n" + i + " passengers,"
					+ (additionArr[0] / ITERATIONS) + ","
					+ (additionArr[1] / ITERATIONS) + ","
					+ (additionArr[2] / ITERATIONS) + ","
					+ (additionArr[3] / ITERATIONS);

			maxAddition += "\n" + i + " passengers,"
					+ (maxAdditionArr[0] / ITERATIONS) + ","
					+ (maxAdditionArr[1] / ITERATIONS) + ","
					+ (maxAdditionArr[2] / ITERATIONS) + ","
					+ (maxAdditionArr[3] / ITERATIONS);

			avgWait += "\n" + i + " passengers,"
					+ (waitArr[0] / ITERATIONS) + ","
					+ (waitArr[1] / ITERATIONS) + ","
					+ (waitArr[2] / ITERATIONS) + ","
					+ (waitArr[3] / ITERATIONS);

			midAddtion += "\n" + i + " passengers,"
					+ (midAdditionArr[0] / ITERATIONS) + ","
					+ (midAdditionArr[1] / ITERATIONS) + ","
					+ (midAdditionArr[2] / ITERATIONS) + ","
					+ (midAdditionArr[3] / ITERATIONS);
		}
		
		String str = "";
		if(areas)
			str = "WithAreas";
		else
			str = "WithNoAreas";
		

		PrintWriter out;

		out = new PrintWriter("avgAddtion" + str + ".csv");
		out.print(avgAddtion);
		out.close();

		out = new PrintWriter("maxAddition" + str + ".csv");
		out.print(maxAddition);
		out.close();

		out = new PrintWriter("avgWait" + str + ".csv");
		out.print(avgWait);
		out.close();

		out = new PrintWriter("midAddtion" + str + ".csv");
		out.print(midAddtion);
		out.close();

		System.out.println("finish");

	}

	public void Run(String method, int passengersInCar, boolean areas) throws Exception {
		Driver.MAX_CAPACITY = passengersInCar;
		if(method.equals("All")){
			passengerChange(areas);
		}
		else{
		String avgAddtion;
		String maxAddition;
		String avgWait;
		String midAddtion;

		double additionArr = 0;
		double maxAdditionArr = 0;
		double waitArr = 0;
		double midAdditionArr = 0;

		avgAddtion = maxAddition = avgWait = midAddtion = "," + method;

		for (int i = 10; i <= PASSENGERS; i += 5) {
			
			additionArr = 0;
			maxAdditionArr = 0;
			waitArr = 0;
			midAdditionArr = 0;

			for (int j = 0; j < ITERATIONS; j++) {
				RequestsUpdate.NUM_OF_REQUESTS = i;

				Drive.NUM_OF_DRIVERS = i / Driver.MAX_CAPACITY;
				Object d = null;
				switch (method){
				case "Naive" :
					d = new DriveMethodOne(areas);
					break;
				case "Static":
					d = new DriveAbsoluteMinimumA(areas);
					break;
				case "Dynamic":
					d = new DriveAbsoluteMinimunUpdating(areas);
					break;
				case "One passenger":
					d = new DriveOndPassengerAtTime(areas);
					break;
				}
				((Drive) d).matchRequestsToDrivers();
				((Drive) d).createRoute();

				Statistics statistics1 = Statistics.statistic(((Drive) d).getDrivers(),	method);

				additionArr += statistics1.getAdditionPrecentage();

					if (additionArr == Double.NaN) {
						throw new Exception("drivers.size(): ");
					}
				
				maxAdditionArr += statistics1.getMaxAddition();
				waitArr += statistics1.getWaitDistace();
				midAdditionArr += statistics1.getMidAddition();

			}

			avgAddtion += "\n" + i + " passengers,"	+ (additionArr / ITERATIONS);

			maxAddition += "\n" + i + " passengers,"+ (maxAdditionArr / ITERATIONS);

			avgWait += "\n" + i + " passengers," + (waitArr / ITERATIONS);

			midAddtion += "\n" + i + " passengers,"	+ (midAdditionArr / ITERATIONS);

		}

		if(areas)
			method = method + "WithAreas";
		else
			method = method + "WithNoAreas";
		
		PrintWriter out;

		out = new PrintWriter("avgAddtion" + method + ".csv");
		out.print(avgAddtion);
		out.close();

		out = new PrintWriter("maxAddition" + method + ".csv");
		out.print(maxAddition);
		out.close();

		out = new PrintWriter("avgWait" + method + ".csv");
		out.print(avgWait);
		out.close();

		out = new PrintWriter("midAddtion" + method + ".csv");
		out.print(midAddtion);
		out.close();

		System.out.println("finish");
		}
	}
}
