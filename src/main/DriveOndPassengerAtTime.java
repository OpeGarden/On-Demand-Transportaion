package main;

import java.util.ArrayList;

public class DriveOndPassengerAtTime extends Drive{

	private int N;
	private int M;

	private int lastMatchDriver;
	private int lastMatchPassenger;


	double[] sumdrive;
	private DistancesMatrix updatingMatrix;

	public  DriveOndPassengerAtTime() {
		super();
		fillMatrix();
		sumdrive=new double[M];
	}

	public DriveOndPassengerAtTime(boolean areas) {
		super(areas);
		fillMatrix();
		sumdrive=new double[M];
	}


	public DriveOndPassengerAtTime(RequestsUpdate requestsUpdate, ArrayList<Driver> drivers) {
		super(requestsUpdate, drivers);
		fillMatrix();
		sumdrive=new double[M];
	}

	public DriveOndPassengerAtTime(RequestsUpdate requestsUpdate, ArrayList<Driver> drivers,boolean areas) {
		super(requestsUpdate, drivers,areas);
		fillMatrix();
		sumdrive=new double[M];
	}

	private void fillMatrix() {
		N = requestsList.size();
		M = drivers.size();

		updatingMatrix = new DistancesMatrix(N, M);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Point p = requestsList.get(i).getOrigin();
				Point p2 = drivers.get(j).getChangeLocation();
				double d = p.distance(p2); 
				updatingMatrix.setNonSquareDistance(i, j, d);
			}
		}
	}

	private void matchAbsoluteMinimum() {
		double min = Double.MAX_VALUE;
		lastMatchPassenger = -1;
		lastMatchDriver=-1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				double d = updatingMatrix.getDistance(i, j)+sumdrive[j];
				if (d < min && !requestsList.get(i).isMatched()) {
					min = d;
					lastMatchPassenger = i;
					lastMatchDriver = j;
				}
			}
		}

		// match
		drivers.get(lastMatchDriver).addPassenger(requestsList.get(lastMatchPassenger));
		drivers.get(lastMatchDriver).setminuscapacity(); 


		updatingMatrix.setNonSquareDistance(lastMatchPassenger, lastMatchDriver, Double.MAX_VALUE);
	}

	public void updateMatrix() {

		Point lastdest=requestsList.get(lastMatchPassenger).getDest();
		double lengthdirve=requestsList.get(lastMatchPassenger).getOrigin().distance(lastdest);
		Point locationLastDriver = drivers.get(lastMatchDriver).getChangeLocation();
		double lengthfromdirver=requestsList.get(lastMatchPassenger).getOrigin().distance(locationLastDriver);
		drivers.get(lastMatchDriver).setChangeLocation(lastdest);
		sumdrive[lastMatchDriver]+=lengthdirve + lengthfromdirver;

		for (int i = 0; i < N; i++) {

			if (!requestsList.get(i).isMatched()) {
				assert(updatingMatrix.getDistance(i, lastMatchDriver) <  Double.MAX_VALUE);

				double var=lastdest.distance(requestsList.get(i).getOrigin());

				updatingMatrix.setNonSquareDistance(i, lastMatchDriver, var);
			}
		}
	}

	@Override
	public void matchRequestsToDrivers() {
		//	Utils.printMatrix(updatingMatrix.distances);
		while(!AllPassengersMatched()){
			matchAbsoluteMinimum();
			updateMatrix();
			//	Utils.printMatrix(updatingMatrix.distances);
		}
	}

	public void createRoute(){
		int i=0;
		for(Driver d : drivers){
			ArrayList<Point> route=new ArrayList<>();
			route.add(d.getLocation());
			for (TransportRequest t : d.getPassengers()){
				route.add(t.getOrigin());
				route.add(t.getDest());
				
			}
			
			d.setRoute(route, sumdrive[i]);
	//		System.out.println("The route is - " + route.toString());
	//		System.out.println("The length of this drive is - " + sumdrive[i]);
			i++;
		}
	

	}

	



}
