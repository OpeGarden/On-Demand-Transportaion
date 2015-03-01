package main;


import java.util.ArrayList;


/**
 * after getting an update we process matching and routes
 * @author Asus
 *
 */
public class Drive { 
	public static final int NUM_OF_DRIVERS =3;
	private RequestsUpdate requestsList;
	private ArrayList<Driver> drivers;
	public RequestsUpdate getRequestsList() {
		return requestsList;
	}

	public void setRequestsList(RequestsUpdate requestsList) {
		this.requestsList = requestsList;
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<Driver> drivers) {
		this.drivers = drivers;
	}

	public Drive(){
		requestsList = new RequestsUpdate();
		drivers = Driver.randomDrivers(NUM_OF_DRIVERS);
	}

	public Drive(RequestsUpdate requestsList, ArrayList<Driver> drivers) {
		this.requestsList = requestsList;
		this.drivers = drivers;
	}

	public void matchRequestsToDrivers() {
		for(Driver d : drivers){
			ArrayList<Integer> requestIndex = new  ArrayList<Integer>();

			int closest = requestsList.findClosestTo(d);
			requestIndex.add(closest);
			d.addPassenger(requestsList.get(closest));
			requestsList.matched(closest);
			System.out.print("\ntesting\ngroupe - " + closest);  //////////////////////////////////

			requestsList.setDistanceMatrices();

			//fill driver car while it has 
			while(!d.isFull()){
				double min = Double.MAX_VALUE;
				int minIndex = -1;
				for (int i : requestIndex){
					int bestForI = requestsList.findBest(i);
					double sumDistance = requestsList.getSumDistance(i,bestForI);
					if(sumDistance < min){
						min = sumDistance;
						minIndex = bestForI;
					} 
				}

				requestIndex.add(minIndex);
				d.addPassenger(requestsList.get(minIndex));
				requestsList.matched(minIndex);
				System.out.print(", " + minIndex);   ///////////////////////////////
			}
			System.out.println();
		}	
	}

	public void createRoute(){
		for(Driver d : drivers){
			d.setDistanceMatrices();
			TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
			ArrayList<TransportRequest> routeOrigins = tspNearestNeighbour.tsp(d.getPassengers(),
					d.getOriginDistances().distances, 0);
			d.setRouteOrigins(routeOrigins);
			int beginDest = d.getClosestToLastOrigin();
			ArrayList<TransportRequest> routeDests = tspNearestNeighbour.tsp(d.getPassengers(),
					d.getDestDistances().distances, beginDest);
			d.setRouteDests(routeDests);

			d.setRoute();
		}
	}

	public static void test(){
		Drive drive	=  new Drive();
	}

	public int size(){
		return drivers.size();
	}

	


	
}






