package main;

import java.util.ArrayList;
import java.util.Arrays;

public class DriveAbsoluteMinimumA extends Drive implements IDrive {
	private int N;
	private int M;
	private MinAbsNode[] minAbsNodes;
	private DistancesMatrix passengerToDriverDistance;


	public DriveAbsoluteMinimumA(){
		super();
		fillPassengerToDriverDistanceMatrix();
	}

	private void fillPassengerToDriverDistanceMatrix() {
		N = requestsList.size();
		M = drivers.size();
		
		minAbsNodes = new MinAbsNode[N*M];
		
		passengerToDriverDistance = new DistancesMatrix(N,M);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Point p = requestsList.get(i).getOrigin();
				Point p2 = drivers.get(j).getLocation();
				double d = p.distance(p2);	
				passengerToDriverDistance.setNonSquareDistance(i,j,d);
				minAbsNodes[i*M+j] = new MinAbsNode(drivers.get(j), requestsList.get(i), d);
			}	
		}
	}

	public DriveAbsoluteMinimumA(RequestsUpdate requestsList, ArrayList<Driver> drivers) {
		this.requestsList = requestsList;
		this.drivers = drivers;
	}


	public void matchRequestsToDrivers() { 
		Arrays.sort(minAbsNodes);
		
		for (int i = 0; i < minAbsNodes.length; i++) {
			MinAbsNode miNode = minAbsNodes[i];
			
			Driver driver = miNode.getDriver();
			
			TransportRequest passenger = miNode.getPassenger();
			
			if(!driver.isFull() && !passenger.isMatched()){
				driver.addPassenger(passenger);
			}
		}
		
		
		for(Driver d : drivers){
			for(TransportRequest tr: d.getPassengers()){
				System.out.print(requestsList.indexOf(tr) + ", ");
				
				//System.out.print(d.getLocation().distance(tr.getOrigin()) + ", ");
			}
			System.out.println();
		}
		
		
		
	}



}
