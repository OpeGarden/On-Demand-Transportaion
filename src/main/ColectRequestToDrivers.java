package main;
import java.util.ArrayList;


public class ColectRequestToDrivers {
/*
	ArrayList<TransportRequest> requests;
	ArrayList<TransportRequest>[] collector; // array of driver, in all index there are list of TransporRequest
	double[][] dist,orign; //mat distance - all request to another. 
	boolean[] gotDriver;  //in start all false, who got driver is true.
	Driver[] driver; //the location and the place in the car
	public ColectRequestToDrivers(ArrayList<TransportRequest> requests,Driver[] driver) {
		// TODO Auto-generated constructor stub
		this.requests=requests;
		collector=(ArrayList<TransportRequest>[]) new ArrayList[driver.length];
		dist=new double[requests.size()][requests.size()];
		orign=new double[requests.size()][requests.size()];
		for (int i = 0; i < dist[0].length; i++) {
			for (int j = 0; j < dist[0].length; j++) {
				dist[i][j]=dist[j][i]=requests.get(i).distanceDest(requests.get(j));
				orign[i][j]=orign[j][i]=requests.get(i).distanceOrigin(requests.get(j));
			}
		}
		gotDriver=new boolean[Main.DRIVER];
		this.driver=driver;


	}

	private ArrayList<TransportRequest>[] colect(){ // need to add chek if finish the all request
		int needToFind=requests.size(); //sum of request that need to collect

		for (int i = 0; i < collector.length; i++) {
			collector[i]=new ArrayList<TransportRequest>();
		}
		for (int idriver = 0; idriver < driver.length; idriver++) {
			if (driver[idriver].place==0) //mean that there are no clear place in idriver car
				break;
			double[] closestRequest=new double[requests.size()];
			for (int i = 0; i < closestRequest.length; i++) { //init to find the closer to the driver
				closestRequest[i]=driver[idriver].location.distance((requests.get(i)).gotOrigin());/////////what is the eror?
			}
			int closer=min(closestRequest);				// need to chek if he got driver--------------------------------------------------------------
			int findForIdriver=driver[idriver].place-1;//-1 because we input the closer to the location
			collector[idriver].add(requests.get(closer));
			gotDriver[closer]=true;
			while (findForIdriver>0){
				findcloser(closestRequest,idriver);//need to impliment
				closer=min(closestRequest);
				gotDriver[closer]=true;
				findForIdriver--;
				collector[idriver].add(requests.get(closer));
			}
			needToFind-=findForIdriver;
			if (needToFind==0)
				return collector;

		}

		return collector;

	}
	private void findcloser(double[] closestRequest, int idriver) {
		
		// change closerRequest to got the real closer to the group of idriver
		for (int i = 0; i < closestRequest.length; i++) {
			if (requests.get(i).equals(collector[idriver].get(0)))
				closestRequest[i]=Integer.MAX_VALUE;
			else
				closestRequest[i]=requests.get(i).distanceDest(collector[idriver].get(0))+
										requests.get(i).distanceOrigin(collector[idriver].get(0));
		}
		for (int i = 0; i < closestRequest.length; i++) {
			for (int j = 0; j < requests.size(); j++) {
				for (int k = 0; k < collector[idriver].size(); k++) { 
					if (!gotDriver[j] || requests.get(j).equals(collector[idriver].get(k)))
						break;
					else
						closestRequest[i]=Math.min(requests.get(j).distanceDest(collector[idriver].get(k))+
								requests.get(j).distanceOrigin(collector[idriver].get(k)), closestRequest[i]);
				}
			}
		}
	}

	public int min(double[] distance){ //find the minimum in the array return the index
		int ans=0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[ans]>distance[i] )
				ans=i;
		}
		return ans;
	}

*/

}
