package main;


public class TransportRequest {
	
	private Point origin;
	private Point dest;
	private boolean matched;
	
	public String toString(){
		return origin.toString() + ":" + dest.toString();
	}
	public TransportRequest(Point origin, Point dest) {
		this.origin = origin;
		this.dest = dest;
	}
	public TransportRequest(char c) { //creates random request
		if(c=='r'){ //r means random
			origin = new Point('r');
			dest = new Point('r');
		}
	}
	public TransportRequest(TransportRequest tr) {
		origin = tr.origin;
		dest = tr.dest;
	}
	public double distanceOrigin(TransportRequest other){//distace from origin to another origin
		return origin.distance(other.origin);
	}
	
	public double distanceDest(TransportRequest other){//distace from destination to another destination
		return dest.distance(other.dest);
	}
	
	public Point getOrigin(){ return origin;}
	public Point getDest(){ return dest;}
	public boolean isMatched(){
		return matched;
	}
	public void matched() {
		matched = true;
	}
	public double distanceOriginToDest(TransportRequest other) {
		return origin.distance(other.dest);
	}
}
