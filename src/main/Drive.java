package main;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Drive { // after getting an update we process matching and routes
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
			System.out.println("matched " + closest);

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
				System.out.println("matched " +  minIndex);
			}
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

	public void statistic() {
		// TODO Auto-generated method stub
		averegeRoudeDrivers();
		avergeRodePassenger();


	}


	private void avergeRodePassenger() {
		// TODO Auto-generated method stub
		int i=0;
		double max=Integer.MIN_VALUE;
		double min=Integer.MAX_VALUE;
		double averge=0;
		for(Driver d : drivers){
			i++;
			double averged=0;
			double mind=Integer.MAX_VALUE;
			double maxd=Integer.MIN_VALUE;
			ArrayList<TransportRequest> dPassengers=d.getPassengers();
			for (TransportRequest tr : dPassengers){
				double diff=(routePasenger(tr, d)-directRoutePasenger(tr, d))/directRoutePasenger(tr, d);
				averged+=diff;
				if (mind>diff)
					mind=diff;
				if (maxd<diff)
					maxd=diff;
			}
			averged=averged/dPassengers.size();
			//System.out.println("driver "+i+" averged is - "+averged);
			//System.out.println("driver "+i+" mind is - "+ mind);
			//System.out.println("driver "+i+" maxd is - "+maxd);

			averge+=averged;
			if (min>mind)
				min=mind;
			if (max<maxd)
				max=maxd;
		}

		averge=averge/(drivers.size());
		System.out.println("the averege roude all passanger are - "+averge);
		System.out.println("the min roude all passanger are - "+min);
		System.out.println("the max roude all passanger are - "+max);


	}


	private void averegeRoudeDrivers() {
		// TODO Auto-generated method stub
		int i=1;
		double max=0;
		double min=Integer.MAX_VALUE;
		double averge=0;
		for(Driver d : drivers){
			double dlength=d.getLengthRoute();
			//System.out.println("driver " + i + " lengthRoute is - "+d.getLengthRoute());
			averge+=dlength;
			i++;
			if (min>dlength)
				min=dlength;
			if (max<dlength)
				max=dlength;
		}
		averge=averge/(i-1);
		System.out.println("the averege roude all drivers are - "+averge);
		System.out.println("the min roude all drivers are - "+min);
		System.out.println("the max roude all drivers are - "+max);


	}

	public double routePasenger(TransportRequest t,Driver dr){
		if (!(dr.getPassengers().contains(t)))
			return -1;
		double ans=0;
		boolean findDist=false;
		Point p=dr.getRoute().get(0);
		int i=1;
		while (p.equals(t.getOrigin())){
			p=dr.getRoute().get(i);
			i++;
		}

		for(;i<dr.getRoute().size() && findDist!=true;i++){
			ans+=p.distance(dr.getRoute().get(i));
			p=dr.getRoute().get(i);
			if (p.equals(t.getDest()))
				findDist=true;
		}
		return ans;
	}

	public double directRoutePasenger(TransportRequest t,Driver dr){
		if (!(dr.getPassengers().contains(t)))
			return -1;
		return t.getOrigin().distance(t.getDest());
	}
}






