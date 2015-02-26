package main;
import java.lang.reflect.Array;
import java.util.ArrayList;




public class Main {
	
	public static void main(String[] args) {
		//UnitTesting.run();
	/*	Point p1o=new Point(0,0);
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
		Driver d1=new Driver(d1locOrg);
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(d1);
		
		TransportRequest Tp1=new TransportRequest(p1o,p1d);
		TransportRequest Tp2=new TransportRequest(p2o,p2d);
		TransportRequest Tp3=new TransportRequest(p3o,p3d);
		TransportRequest Tp4=new TransportRequest(p4o,p4d);
		
		TransportRequest Tp5=new TransportRequest(p7o,p4d);
		TransportRequest Tp6=new TransportRequest(p5o,p2d);
		TransportRequest Tp7=new TransportRequest(p6o,p1d);
		TransportRequest Tp8=new TransportRequest(p8o,p3d);
		
		ArrayList<TransportRequest> group1 = new ArrayList<TransportRequest>();
		group1.add(Tp1);group1.add(Tp2);group1.add(Tp3);group1.add(Tp4);

		TransportRequest[] requests = new TransportRequest[8];
		requests[0] = Tp1;
		requests[1] = Tp2;
		requests[2] = Tp3;
		requests[3] = Tp4;
		requests[4] = Tp5;
		requests[5] = Tp6;
		requests[6] = Tp7;
		requests[7] = Tp8;
		
		RequestsUpdate update = new RequestsUpdate(requests);
		Drive d = new Drive();
		d.matchRequestsToDrivers();
		d.createRoute();
		UnitTesting.testStatistic();
		DrawDrive.draw(d, 100, 100);
		
		*/
		TransportRequest[] requests = new TransportRequest[8];

		RequestsUpdate update = new RequestsUpdate(requests);
		Drive d = new Drive();
		d.matchRequestsToDrivers();
		d.createRoute();
		System.out.println();
		d.statistic();
		
		DrawDrive.draw(d, 100, 100);

	}

}

