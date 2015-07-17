package main;

import java.util.ArrayList;

public class DriveAbsoluteMinimunUpdating extends Drive {

	private int N;
	private int M;

	private int lastMatchDriver;
	private int lastMatchPassenger;



	private DistancesMatrix updatingMatrix;

	public DriveAbsoluteMinimunUpdating() {
		super();
		fillMatrix();
	}

	public DriveAbsoluteMinimunUpdating(boolean areas) {
		super(areas);
		fillMatrix();
	}

	
	public DriveAbsoluteMinimunUpdating(RequestsUpdate requestsUpdate, ArrayList<Driver> drivers) {
		super(requestsUpdate, drivers);
		fillMatrix();
	}
	
	public DriveAbsoluteMinimunUpdating(RequestsUpdate requestsUpdate, ArrayList<Driver> drivers,boolean areas) {
		super(requestsUpdate, drivers,areas);
		fillMatrix();
	}

	private void fillMatrix() {
		N = requestsList.size();
		M = drivers.size();

		updatingMatrix = new DistancesMatrix(N, M);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Point p = requestsList.get(i).getOrigin();
				Point p2 = drivers.get(j).getLocation();
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
				double d = updatingMatrix.getDistance(i, j);
				if (d < min && !drivers.get(j).isFull() && !requestsList.get(i).isMatched()) {
					min = d;
					lastMatchPassenger = i;
					lastMatchDriver = j;
				}
			}
		}

		// match
		drivers.get(lastMatchDriver).addPassenger(requestsList.get(lastMatchPassenger));

		updatingMatrix.setNonSquareDistance(lastMatchPassenger, lastMatchDriver, Double.MAX_VALUE);
	}

	public void updateMatrix() {
		for (int i = 0; i < N; i++) {

			if (!requestsList.get(i).isMatched()) {
				assert(updatingMatrix.getDistance(i, lastMatchDriver) <  Double.MAX_VALUE);
				// for every passenger that matched, find the passenger that is
				// best to current passenger i
				double min = Double.MAX_VALUE;
				for (TransportRequest tr : drivers.get(lastMatchDriver).getPassengers()) {
					double sumDistance = tr.getSumDistance(requestsList.get(i), 0);
					min = Math.min(min, sumDistance);
				}

				updatingMatrix.setNonSquareDistance(i, lastMatchDriver, min);
			}
		}
	}

	@Override
	public void matchRequestsToDrivers() {
	//	Utils.printMatrix(updatingMatrix.distances);
		while(!AllDriversFull() && !AllPassengersMatched()){
			matchAbsoluteMinimum();
			updateMatrix();
		//	Utils.printMatrix(updatingMatrix.distances);
		}
	}



}
