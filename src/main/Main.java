package main;

import java.io.FileNotFoundException;


import java.util.ArrayList;

import main.Statistics;

public class Main {

	public static void main(String[] args) {

		//maingraph();
		main0();
		
		System.out.println("---end---");

	}
	
	public static void maintest(){
	Point p1o=new Point(0,0);
	Point p1d=new Point(5,5);
	Point p2o=new Point(1,0);
	Point p2d=new Point(4,5);
	Point p3o=new Point(0,1);
	Point p3d=new Point(4,4);
	Point p4o=new Point(1,1);
	Point p4d=new Point(5,4);
	Point p5o = new Point(0,5);
	Point p6o = new Point(1,5);
	Point p7o = new Point(0,4);
	Point p8o = new Point(1,4);
	
	Point d1locOrg=new Point (2,0);
	Point d2locOrg=new Point (3,3);
	Driver d1=new Driver(d1locOrg);
	Driver d2=new Driver(d2locOrg);
	ArrayList<Driver> drivers = new ArrayList<Driver>();
	drivers.add(d1);
	drivers.add(d2);
	
	TransportRequest Tp1=new TransportRequest(p1o,p1d);
	TransportRequest Tp2=new TransportRequest(p2o,p2d);
	TransportRequest Tp3=new TransportRequest(p3o,p3d);
	TransportRequest Tp4=new TransportRequest(p4o,p4d);
		
	//ArrayList<TransportRequest> group1 = new ArrayList<TransportRequest>();
	//group1.add(Tp1);group1.add(Tp2);group1.add(Tp3);group1.add(Tp4);

	TransportRequest[] requests = new TransportRequest[4];
	requests[0] = Tp1;
	requests[1] = Tp2;
	requests[2] = Tp3;
	requests[3] = Tp4;
	
	RequestsUpdate update = new RequestsUpdate(requests);
	DriveOndPassengerAtTime d= new DriveOndPassengerAtTime(update, drivers);
	d.matchRequestsToDrivers();
	d.createRoute();
	//Statistics.statistic(d.getDrivers(), "asfasf");

	}
	
	public static void main0(){	
		DriveAbsoluteMinimumA d = new DriveAbsoluteMinimumA();
		DriveMethodOne d2 = new DriveMethodOne(d.getRequestsList(), d.getDrivers());
		DriveAbsoluteMinimunUpdating d3 = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
		DriveOndPassengerAtTime d4=new DriveOndPassengerAtTime(d.getRequestsList(), d.getDrivers());
		sampleRun(d,"Static");
		sampleRun(d2,"Naive");
		sampleRun(d3,"Dynamic");
		sampleRun(d4,"One passenger");
	}

	public static void maingraph(){
		try {
			Graphs.passengerChange(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main5(String method, int numPassenger, int passengersInCar, boolean areas){
		Drive d;
		updateParameters(numPassenger,passengersInCar);
		switch (method){
		case "Naive" :
			d = new DriveMethodOne(areas);
			sampleRun(d,method);
			break;
		case "Static" :
			 d = new DriveAbsoluteMinimumA(areas);
			 sampleRun(d,method);
			break;
		case "Dynamic" :
			d = new DriveAbsoluteMinimunUpdating(areas);
			sampleRun(d,method);
			break;
		case "One passenger" :
			d = new DriveOndPassengerAtTime(areas);
			sampleRun(d,method);
			break;
		  default:
			  d = new DriveMethodOne(areas);
			  sampleRun(d,"Naive");
			  d = new DriveAbsoluteMinimumA(d.getRequestsList(), d.getDrivers());
			  sampleRun(d,"Static");
			  d = new DriveAbsoluteMinimunUpdating(d.getRequestsList(), d.getDrivers());
			  sampleRun(d,"Dynamic");
			  d = new DriveOndPassengerAtTime(d.getRequestsList(), d.getDrivers());
			  sampleRun(d,"One passenger");
			break;
			
		}
	}

	private static void updateParameters(int numPassenger, int passengersInCar) {
		Driver.MAX_CAPACITY = passengersInCar;
		RequestsUpdate.NUM_OF_REQUESTS = numPassenger;
		Drive.NUM_OF_DRIVERS = numPassenger / passengersInCar;
	}

	private static void sampleRun(Drive d, String method){
		d.matchRequestsToDrivers();
		d.createRoute();
		Statistics.statistic(d.getDrivers(), method);
		DrawDrive.draw(method, d, false);
	}
}
