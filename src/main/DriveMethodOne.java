package main;

import java.util.ArrayList;

public class DriveMethodOne extends Drive {
	
	public DriveMethodOne(RequestsUpdate update, ArrayList<Driver> drivers) {
		super(update, drivers);
	}

	public DriveMethodOne() {
		super();
	}

	public void matchRequestsToDrivers() { // the fitst function
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

}
