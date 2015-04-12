package main;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class DrawDrive {
	public static Color randomColor(){
		Random r = new Random();
		int a = r.nextInt(200);
		int b = r.nextInt(200);
		int c = r.nextInt(200);
		float[] arr = Color.RGBtoHSB(a,b,c,null);
		return Color.getHSBColor(arr[0], arr[1], arr[2]);
	}
	public static void draw(String title, IDrive drive) {
		RequestsUpdate requestsList = drive.getRequestsList();
		ArrayList<Driver> drivers = drive.getDrivers();
		Plot graphi = new Plot(title, -2, Point.LIMIT, 1, -2, Point.LIMIT, 1); // make new graph


		for(Driver d : drive.getDrivers()){

			graphi.setColor(Color.DARK_GRAY); // change color of the point to yellow(driver point)
			graphi.addPoint(d.getLocation().getX(), d.getLocation().getY(),drive.getDrivers().indexOf(d)); // add the point to the graph
			for(TransportRequest tr : d.getPassengers()){
				int index = Arrays.asList(requestsList.getRequest()).indexOf(tr);
				graphi.setColor(Color.blue); // change color of the point to blue(origin point)
				graphi.addPoint(tr.getOrigin().getX(), tr.getOrigin().getY(),index); // add the point to the graph
				graphi.setColor(Color.red);  // change color of the point to red(dest point)
				graphi.addPoint(tr.getDest().getX(),tr.getDest().getY(),index); // add the point to the graph
			}
			ArrayList<Point> routeExample = d.getRoute();
			if(routeExample != null){
				graphi.setColor(randomColor());
				for (int i = 1; i < routeExample.size(); i++) {
					graphi.addLine(routeExample.get(i-1), routeExample.get(i) );
				}	
			}
		}


	}

}
