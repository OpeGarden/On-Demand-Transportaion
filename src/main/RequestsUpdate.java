package main;

public class RequestsUpdate { //new "stack" of requests
	public static int NUM_OF_REQUESTS = 10;
	private TransportRequest[] requests;
	private DistancesMatrix originDistances;
	private DistancesMatrix destDistances;

	private int matchedCounter;
	
	public DistancesMatrix getOriginDistances() {
		return originDistances;
	}
	public DistancesMatrix getDestDistances() {
		return destDistances;
	}

	public int size(){
		return requests.length;
	}
	
	public RequestsUpdate(){
		this(randomTransportRequestsAreas(NUM_OF_REQUESTS));
	}
	
	public RequestsUpdate(TransportRequest[] requests) {
		this.requests = requests;
		matchedCounter = 0;
	}

	
	
	public static TransportRequest[] randomTransportRequestsAreas(int num){
		TransportRequest[] arr = new TransportRequest[num];
		for (int i = 0; i < num; i++) {
			arr[i] = new TransportRequest('a');
		}
		return arr;
	}
	
	public static TransportRequest[] randomTransportRequests(int num){
		TransportRequest[] arr = new TransportRequest[num];
		for (int i = 0; i < num; i++) {
			arr[i] = new TransportRequest('r');
		}
		return arr;
	}
	
	public void setDistanceMatrices(){
		originDistances = new DistancesMatrix(size());
		destDistances = new DistancesMatrix(size());
		for (int i = 0; i < size(); i++) {
			for (int j = i+1; j < size(); j++) {
				double origin = requests[i].distanceOrigin(requests[j]);
				double dest = requests[i].distanceDest(requests[j]);
				originDistances.setDistance(i, j, origin);
				destDistances.setDistance(i, j, dest);
			}
		}
	}
	public double[] distacesFromDriver(Driver d) {
		double[] distances = new double[size()];
		for (int k=0, i = 0; i < size(); i++) {
			TransportRequest r = requests[i];
			if(!r.isMatched()){
				distances[k] = d.distanceFromRequestOrigin(r);
				k++;
			}
		}
		return distances;
	}
	/*
	public double[] distacesFromDriver(Driver d) {
		double[] distances = new double[remaining];
		for (int k=0, i = 0; i < size() && k < remaining; i++) {
			TransportRequest r = requests[i];
			if(!r.isMatched()){
				distances[k] = d.distanceFromRequestOrigin(r);
				k++;
			}
		}
		return distances;
	}*/
	public int findClosestTo(Driver d) {
		double[] distances = distacesFromDriver(d);
		int ans=-1;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < distances.length; i++) {
			if(isMatched(i)) continue;
			if (distances[i] < min) {
				ans=i;
				min = distances[i];
			}
		}
		if(ans == -1){
			System.out.println("fatal error");
		}
		return ans;
	}
	
	
	public TransportRequest get(int i) {
		return requests[i];
	}
	public void matched(int i) {
		matchedCounter++;
		requests[i].matched();	
	}
	public int findBest(int i) {
		double min = Double.MAX_VALUE;
		int minIndex = -1;
		for (int j = 0; j <size(); j++) {
			if(isMatched(j) || i==j) continue;
			double sum = originDistances.getDistance(i, j)+destDistances.getDistance(i, j);
			if(sum<min){
				min =sum;
				minIndex = j;
			}
		}
		return minIndex;
	}
	private boolean isMatched(int j) {
		return requests[j].isMatched();	
	}
	public double getSumDistance(int i, int j, double d) {
		return d*originDistances.getDistance(i, j)+ (1-d)*destDistances.getDistance(i, j);
	}
	public TransportRequest[] getRequest() {
		return requests	;
	}
	public int indexOf(TransportRequest tr) {
		for (int i = 0; i < requests.length; i++) {
			if(requests[i] == tr) return i;
		}
		return -1;
	}
	public boolean areAllMatched() {
		return matchedCounter == size();
	}
	
}
