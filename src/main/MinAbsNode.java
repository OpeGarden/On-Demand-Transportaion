package main;

import java.util.Comparator;

public class MinAbsNode implements Comparable<MinAbsNode> {
	
	private Driver driver;
	private TransportRequest passenger;
	private double dist;
	
	public MinAbsNode(Driver driver, TransportRequest passenger, double dist) {
		this.driver = driver;
		this.passenger = passenger;
		this.dist = dist;
	}
	
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public TransportRequest getPassenger() {
		return passenger;
	}
	public void setPassenger(TransportRequest passenger) {
		this.passenger = passenger;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	
	public String toString(){
		return "" + dist;
	}


	@Override
	public int compareTo(MinAbsNode other) {
		return Double.compare(dist, other.dist);
	}
	
	
	
	

}
