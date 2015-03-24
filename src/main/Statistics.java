package main;

import java.util.ArrayList;

public class Statistics {

	private static ArrayList<Driver> drivers;
	private static Plot a = new Plot(-2, Point.LIMIT, 1, -2, Point.LIMIT, 1);
	public static void statistic(ArrayList<Driver> d) {

		drivers = d;   
		/**
		 * make test of statistic
		 * 
		 *
		 */
		// TODO Auto-generated method stub
		a.setText("DRIVER\n");
		averegeRoudeDrivers();
		a.setText("\n \n PASSENGER \n");
		avergeRodePassenger();
	}


	private static void avergeRodePassenger() {
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
				double route=routePasenger(tr, d);
				double direct=directRoutePasenger(tr, d);
				double diff=route-direct;
				double yahas=diff/route;
				averged+=yahas;
				if (mind>yahas)
					mind=yahas;
				if (maxd<yahas)
					maxd=yahas;
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
		a.setText("\n averege roude all passanger are - " + averge);
		a.setText("\n min roude all passanger are - "+min);
		a.setText("\n max roude all passanger are - "+max);
	}


	private static void averegeRoudeDrivers() {
		// TODO Auto-generated method stub
		double max=0;
		double min=Integer.MAX_VALUE;
		double averge=0;
		int indexofmax=0;
		int indexofmin=0;
		int i=0;
		for(Driver d : drivers){
			double dlength=d.getLengthRoute();
			//System.out.println("driver " + i + " lengthRoute is - "+d.getLengthRoute());
			averge+=dlength;
			if (min>dlength){
				min=dlength;
				indexofmin=i;
			}
			
			if (max<dlength){
				max=dlength;
				indexofmax=i;
			}
			i++;
		}
		averge=averge/drivers.size();
		a.setText("\n averege roude all drivers are - "+averge);
		a.setText("\n min roude all drivers are - "+min + " index of min" + indexofmin);
		a.setText("\n max roude all drivers are - "+max + " index of max" + indexofmax);
	}

	public static double routePasenger(TransportRequest t,Driver dr){
		if (!(dr.getPassengers().contains(t)))
			return -1;
		double ans=0;
		boolean findDist=false;
		Point p=dr.getRoute().get(0);
		int i=1;
		while (!p.equals(t.getOrigin())){
			p=dr.getRoute().get(i);
			i++;
		}

		while( findDist != true) {
			ans+=p.distance(dr.getRoute().get(i));
			p=dr.getRoute().get(i);
			if (p.equals(t.getDest()))
				findDist=true;
			i++;
		}
		return ans;
	}

	public static double directRoutePasenger(TransportRequest t,Driver dr){
		if (!(dr.getPassengers().contains(t)))
			return -1;
		return t.getOrigin().distance(t.getDest());
	}
}
