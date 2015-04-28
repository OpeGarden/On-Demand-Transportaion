package main;

import java.util.ArrayList;

public class DriveMethodOne extends Drive {
	private int driverCounter = 0;
	public DriveMethodOne(RequestsUpdate update, ArrayList<Driver> drivers) {
		super(update, drivers);
	}

	public DriveMethodOne() {
		super();
	}

	public void matchRequestsToDrivers() { // the fitst function
		for(Driver d : drivers){
			if(requestsList.areAllMatched()) break;
			//System.out.println("match Requests To driver number: " + driverCounter++);
			ArrayList<Integer> requestIndex = new  ArrayList<Integer>();

			int closest = requestsList.findClosestTo(d);
			if(closest == -1) continue;
			requestIndex.add(closest);
			d.addPassenger(requestsList.get(closest));
			requestsList.matched(closest);
			requestsList.setDistanceMatrices();

			//fill driver car while it has 
			while(!d.isFull() && !requestsList.areAllMatched()){
				double min = Double.MAX_VALUE;
				int minIndex = -1;
				for (int i : requestIndex){
					int bestForI = requestsList.findBest(i);
					double sumDistance = requestsList.getSumDistance(i,bestForI,0.5);
					if(sumDistance < min){
						min = sumDistance;
						minIndex = bestForI;
					} 
				}

				requestIndex.add(minIndex);
				d.addPassenger(requestsList.get(minIndex));
				requestsList.matched(minIndex);
				//System.out.print(", " + minIndex);   ///////////////////////////////
			}
		}	
	}

}
