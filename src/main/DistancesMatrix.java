package main;


public class DistancesMatrix {

	double[][] distances;
	
	public DistancesMatrix(int n){
		distances = new double[n][n];
	}
	
	public void setDistance(int i, int j, double distance){
		assert(distance > 0);
		distances[i][j] = distance;
		distances[j][i] = distance;
	}

	public double getDistance(int i, int j) {
		return distances[i][j];
	}

	public int vSize() {
		
		return distances.length;
	}

}