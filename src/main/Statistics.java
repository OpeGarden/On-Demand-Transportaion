package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Statistics {
	private double additionPrecentage;
	private double waitDistace;
	private double driverDistance;
	private double maxAddition;
	private double midAddition;

	public double getMidAddition() {
		return midAddition;
	}

	public void setMidAddition(double midAddition) {
		this.midAddition = midAddition;
	}

	public double getMaxAddition() {
		return maxAddition;
	}

	public void setMaxAddition(double maxAddition) {
		this.maxAddition = maxAddition;
	}

	public double getAdditionPrecentage() {
		return additionPrecentage;
	}

	public void setAdditionPrecentage(double additionPrecentage) {
		this.additionPrecentage = additionPrecentage;
	}

	public double getWaitDistace() {
		return waitDistace;
	}

	public void setWaitDistace(double waitDistace) {
		this.waitDistace = waitDistace;
	}

	public double getDriverDistance() {
		return driverDistance;
	}

	public void setDriverDistance(double driverDistance) {
		this.driverDistance = driverDistance;
	}

	private static ArrayList<Driver> drivers;
	private static Plot a = new Plot(-2, Point.LIMIT, 1, -2, Point.LIMIT, 1);

	public static Statistics statistic(ArrayList<Driver> d, String name) {
		Statistics me = new Statistics();

		drivers = d;
		/**
		 * make test of statistic
		 * 
		 *
		 */
		// TODO Auto-generated method stub
		a.setText("\n\n\n" + name + "\n");
		a.setText("\n DRIVER\n");
		averegeRoudeDrivers(me);
		a.setText("\n \n PASSENGER \n");
		try {
			avergeRodePassenger(me);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return me;
	}

	private static void avergeRodePassenger(Statistics me) throws Exception {
		// TODO Auto-generated method stub
		int i = 0;
		double max = Integer.MIN_VALUE;
		double min = Integer.MAX_VALUE;
		double averge = 0;
		double avergeWait = 0;
		int sumpassenger = 0;

		ArrayList<Double> ratios = new ArrayList<Double>();

		for (Driver d : drivers) {
			sumpassenger += d.getPassengers().size();
			i++;
			double averged = 0;
			// double avergeWaitd=0;
			double mind = Integer.MAX_VALUE;
			double maxd = Integer.MIN_VALUE;
			ArrayList<TransportRequest> dPassengers = d.getPassengers();
			for (TransportRequest tr : dPassengers) {
				double route = routePasenger(tr, d);
				double direct = directRoutePasenger(tr, d);
				double diff = route - direct;
				double yahas = diff / route;
				if(route == 0){
					throw new Exception("route 0");
				}
				ratios.add(yahas);
				averged += yahas;
				avergeWait += waitToDrive(tr, d);
				if (mind > yahas)
					mind = yahas;
				if (maxd < yahas)
					maxd = yahas;
			}

			if (dPassengers.size() != 0) {
				averged = averged / dPassengers.size();

				averge += averged;
				// avergeWait+=avergeWaitd;
				if (min > mind)
					min = mind;
				if (max < maxd)
					max = maxd;
			}

		}

		averge = averge / (drivers.size());
		if(((Double)averge).isNaN()){
			throw new Exception("averge NaN");
		}

		avergeWait /= sumpassenger;
		a.setText("\n averege roude all passanger are - " + averge);
		a.setText("\n averege waited to collect all driver are - " + avergeWait);
		a.setText("\n min roude all passanger are - " + min);
		a.setText("\n max roude all passanger are - " + max);

		me.setAdditionPrecentage(averge);
		me.setWaitDistace(avergeWait);
		me.setMaxAddition(max);

		Object[] arr = ratios.toArray();

		Arrays.sort(arr, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return Double.compare((Double) o1, (Double) o2);
			}
		});

		me.setAdditionMid((Double) arr[arr.length / 2]);

	}

	private void setAdditionMid(Double mid) {
		this.midAddition = mid;

	}

	private static void averegeRoudeDrivers(Statistics me) {
		// TODO Auto-generated method stub
		double max = 0;
		double min = Integer.MAX_VALUE;
		double averge = 0;
		int indexofmax = 0;
		int indexofmin = 0;
		int i = 0;
		for (Driver d : drivers) {
			double dlength = d.getLengthRoute();
			// System.out.println("driver " + i +
			// " lengthRoute is - "+d.getLengthRoute());
			averge += dlength;
			if (min > dlength) {
				min = dlength;
				indexofmin = i;
			}

			if (max < dlength) {
				max = dlength;
				indexofmax = i;
			}
			i++;
		}
		averge = averge / drivers.size();
		a.setText("\n averege roude all drivers are - " + averge);
		a.setText("\n min roude all drivers are - " + min + " index of min"
				+ indexofmin);
		a.setText("\n max roude all drivers are - " + max + " index of max"
				+ indexofmax);

		me.setDriverDistance(averge);
	}

	public static double routePasenger(TransportRequest t, Driver dr) {
		if (!(dr.getPassengers().contains(t)))
			return -1;
		double ans = 0;
		boolean findDist = false;
		Point p = dr.getRoute().get(0);
		int i = 1;
		while (!p.equals(t.getOrigin())) {
			p = dr.getRoute().get(i);
			i++;
		}

		while (findDist != true) {
			ans += p.distance(dr.getRoute().get(i));
			p = dr.getRoute().get(i);
			if (p.equals(t.getDest()))
				findDist = true;
			i++;
		}
		if(ans == 0){
			System.out.println("ans 0");
			System.out.println(t.toString());
			System.out.println(dr.toString());
		}
		return ans;
	}

	public static double directRoutePasenger(TransportRequest t, Driver dr) {
		if (!(dr.getPassengers().contains(t)))
			return -1;
		return t.getOrigin().distance(t.getDest());
	}

	public static double waitToDrive(TransportRequest t, Driver dr) {
		if (!(dr.getPassengers().contains(t)))
			return -1;
		double ans = 0;
		Point p = dr.getRoute().get(0);
		int i = 1;
		while (!p.equals(t.getOrigin())) {
			Point q = dr.getRoute().get(i);
			i++;
			ans += p.distance(q);
			p = q;
		}
		return ans;
	}
}
