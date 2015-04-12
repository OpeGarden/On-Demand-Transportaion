package main;
import java.util.Random;


/*
 *שלום חברס!!עקרונית שידרגתי קצת המחלקה שאני אצטרך להרחיב בהמשך
 *בכל מקרה אני חושב שצריך להעביר את המחלקה לדאבל
 *יאללה ביי להיום 17.12 איתמר 
 * 
 */


public class Point {
	private double x;
	private double y;
	public static final int LIMIT = 100;
	
	public Point(double d,double e){
		this.x = d;
		this.y = e;
	}
	public Point(char c){// creates random point
		if(c == 'r'){//r means random
			Random rand = new Random();
			x = rand.nextInt(LIMIT);
			y = rand.nextInt(LIMIT);
		}
		else if(c == 'o'){
			Random rand = new Random();
			x = rand.nextInt(LIMIT/2);
			y = rand.nextInt(LIMIT/2);
		}
		else if(c == 'd'){
			Random rand = new Random();
			x = rand.nextInt(LIMIT/2) + LIMIT/2;
			y = rand.nextInt(LIMIT/2) + LIMIT/2;
		}
	}
	
	
	public Point(Point p){
		x = p.x;
		y = p.y;
	}
	
	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y; 
	}
	
	public double distance(Point p){
		double power = Math.pow(x - p.x, 2) + Math.pow(y - p.y,2);
		double d = Math.sqrt(power);
		return d;
	}
	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
	public boolean equals(Point p){
		if (this.x==p.getX() && this.y==p.getY()) 
			return true;
		return false;
	}

}
