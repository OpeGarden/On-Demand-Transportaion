package main;


import java.util.ArrayList;


/**
 * after getting an update we process matching and routes
 * @author Asus
 *
 */
public abstract class Drive implements IDrive { 
	public static final int NUM_OF_DRIVERS =3;
	protected RequestsUpdate requestsList;
	
	/**
	 * distance matrix between drivers and passengers
	 */
	protected DistancesMatrix PassengerToDriverDistance;

	protected ArrayList<Driver> drivers;
	
	
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
		
		
		this.requestsList = new RequestsUpdate();
		this.drivers = new ArrayList<Driver>();
		
		for(Driver d: drivers){
			this.drivers.add(new Driver(d));
		}
		
		
		TransportRequest[] requests = new TransportRequest[requestsList.size()];
		int i =0;
		for(TransportRequest tr : requestsList.getRequest()){		
			requests[i++] = new TransportRequest(tr);
		}
		
		this.requestsList = new RequestsUpdate(requests);
		
	}
	
	public void matchMethodAbsoluteMinimum(){
		
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

	public int size(){
		return drivers.size();
	}

	public abstract void matchRequestsToDrivers();

	


	
}






