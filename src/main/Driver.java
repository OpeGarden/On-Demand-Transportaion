package main;
import java.util.ArrayList;


public class Driver {


	public static int MAX_CAPACITY = 5;
	private int capacity; // the place in the car. 
	private Point location; //the location of the driver.
	private ArrayList<TransportRequest> passengers;
	
	private DistancesMatrix originDistances;
	private DistancesMatrix destDistances;
	
	private ArrayList<TransportRequest> routeOrigins;
	private ArrayList<TransportRequest> routeDests;
	
	private ArrayList<Point> route;
	
	private double lengthRoute;
	
	public ArrayList<TransportRequest> getPassengers() {
		return passengers;
	}
	public String toString(){
		String s = "";
		s += "passengers:\n";
		for(TransportRequest tr: passengers){
			s += tr.toString() + "\n";
		}
		return s;
	}
	boolean isFull(){
		return capacity == MAX_CAPACITY;
	}

	public Driver(Point location){
		passengers = new ArrayList<TransportRequest>();
		capacity=0;
		this.location=location;
	}
	public Driver(){
		this(new Point('r'));
	}
	public Driver(Driver d) {
		this(d.location);
	}
	public void addPassenger(TransportRequest e){	
		assert(!isFull());
		if(e.isMatched()){
			try {
				throw new Exception();
			} catch (Exception e1) {
				e1.printStackTrace();
				System.exit(0);
			}
		}
		e.matched();
		passengers.add(e);
		capacity++;
	}

	public void setLocation(Point other){
		location.setX(other.getX());
		location.setY(other.getY());
	}
	public Point getLocation(){ 
		return location;  
	}

	public static ArrayList<Driver> randomDrivers(int num){
		ArrayList<Driver> ans = new ArrayList<Driver>();
		for (int i = 0; i < num; i++) {
			ans.add(new Driver());
		}
		return ans;
	}

	public double distanceFromRequestOrigin(TransportRequest r) {
		return location.distance(r.getOrigin());
	}
	public static boolean assertEquals(ArrayList<TransportRequest> group1,ArrayList<TransportRequest> group2) {
		for(TransportRequest tr : group1){
			if(!group2.contains(tr)) return false;
		}
		return true;
	}
	public void setDistanceMatrices(){
		originDistances = new DistancesMatrix(size());
		destDistances = new DistancesMatrix(size());
		for (int i = 0; i < size(); i++) {
			for (int j = i+1; j < size(); j++) {
				double origin = passengers.get(i).distanceOrigin(passengers.get(j));
				double dest = passengers.get(i).distanceDest(passengers.get(j));
				originDistances.setDistance(i, j, origin);
				destDistances.setDistance(i, j, dest);
			}
		}
	}
	private int size() {
		return passengers.size();
	}
	public DistancesMatrix getOriginDistances() {
		return originDistances;
	}
	public DistancesMatrix getDestDistances() {
		return destDistances;
	}
	public void setRouteOrigins(ArrayList<TransportRequest> routeOrigins) {
		this.routeOrigins = routeOrigins;

	}
	public void setRouteDests(ArrayList<TransportRequest> routeDests) {
		this.routeDests = routeDests;

	}
	public int getClosestToLastOrigin() {
		TransportRequest lastOrigin = routeOrigins.get(size()-1);
		double min = Double.MAX_VALUE;
		int closest = -1;
		for (int i = 0; i < size(); i++) {
			TransportRequest tr = getPassenger(i);
			double distance = lastOrigin.distanceOriginToDest(tr);
			if(distance < min){
				closest = i;
				min = distance;
			}
		}
		return closest;
	}
	private TransportRequest getPassenger(int i) {
		return passengers.get(i);
	}
	
	public void setRoute() {
		route = new ArrayList<Point>();
		route.add(location);
		for(TransportRequest tr : routeOrigins){
			route.add(tr.getOrigin());
		}
		for(TransportRequest tr : routeDests){
			route.add(tr.getDest());
		}
		getDistDrive();
	}
	public ArrayList<Point> getRoute() {
		return route;
	}
	
	public void getDistDrive(){
		if (this.route.isEmpty()){
			lengthRoute= -1;
		}
		double ans=0;
		Point p=route.get(0);
		
		for(int i=1;i<route.size();i++){
			ans+=p.distance(route.get(i));
			p=route.get(i);
		}
		lengthRoute= ans;
	}
	
	
	public double getLengthRoute(){
		return lengthRoute;
	}
	public boolean isEmpty() {
		return size() == 0;
	}

}


