package main;
import java.util.Random;

import com.google.maps.model.LatLng;


/*
 *שלום חברס!!עקרונית שידרגתי קצת המחלקה שאני אצטרך להרחיב בהמשך
 *בכל מקרה אני חושב שצריך להעביר את המחלקה לדאבל
 *יאללה ביי להיום 17.12 איתמר 
 * 
 */


public class Point extends LatLng {	
	public static final int LIMIT = 200;
	
	public Point(double d,double e){
		super(d, e);
	}
	public Point(char c){// creates random point
		super(0, 0);
		if(c == 'r'){//r means random
			Random rand = new Random();
			lat = rand.nextInt(LIMIT);
			lng = rand.nextInt(LIMIT);
		}
		else if(c == 'o'){
			Random rand = new Random();
			lat = rand.nextInt(LIMIT/2);
			lng = rand.nextInt(LIMIT/2);
		}
		else if(c == 'd'){
			Random rand = new Random();
			lat = rand.nextInt(LIMIT/2) + LIMIT/2;
			lng = rand.nextInt(LIMIT/2) + LIMIT/2;
		}
	}
	
	
	public Point(Point p){
		super(0,0);
		lat = p.lat;
		lng = p.lng;
	}
	
	public double getX(){
		return lat;
	}

	public double getY(){
		return lng;
	}
	
	public void setX(double x){
		this.lat = x;
	}
	
	public void setY(double y){
		this.lng = y; 
	}
	
	public double distance(Point p){
		double power = Math.pow(lat - p.lat, 2) + Math.pow(lng - p.lng,2);
		double d = Math.sqrt(power);
		return d;
	}
	
	public String toString(){
		return "(" + lat + "," + lng + ")";
	}
	public boolean equals(Point p){
		if (this.lat==p.lat && this.lng==p.lng) 
			return true;
		return false;
	}

}
