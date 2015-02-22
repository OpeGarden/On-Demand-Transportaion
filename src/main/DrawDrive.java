package main;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class DrawDrive {
	public static Color randomColor(){
		Random r = new Random();
		int a = r.nextInt(200);
		int b = r.nextInt(200);
		int c = r.nextInt(200);
		float[] arr = Color.RGBtoHSB(a,b,c,null);
		System.out.println(a+ " " + b + " " + c);
		return Color.getHSBColor(arr[0], arr[1], arr[2]);
	}
	public static void draw(Drive drive, double xlim, double ylim) {
		RequestsUpdate requestsList = drive.getRequestsList();
		ArrayList<Driver> drivers = drive.getDrivers();
		Plot graphi = new Plot("graph", -2, Point.LIMIT, 1, -2, Point.LIMIT, 1); // make new graph
		
		
		for(Driver d : drive.getDrivers()){
			
			graphi.setColor(Color.yellow); // change color of the point to yellow(driver point)
			graphi.addPoint(d.getLocation().getX(), d.getLocation().getY()); // add the point to the graph
			for(TransportRequest tr : d.getPassengers()){
				graphi.setColor(Color.blue); // change color of the point to blue(origin point)
				graphi.addPoint(tr.getOrigin().getX(), tr.getOrigin().getY()); // add the point to the graph
				graphi.setColor(Color.red);  // change color of the point to red(dest point)
				graphi.addPoint(tr.getDest().getX(),tr.getDest().getY()); // add the point to the graph
			}
			ArrayList<Point> routeExample = d.getRoute();
			graphi.setColor(randomColor());
			for (int i = 1; i < routeExample.size(); i++) {
				graphi.addLine(routeExample.get(i-1), routeExample.get(i) );
			}	
		}

		
	}

}
