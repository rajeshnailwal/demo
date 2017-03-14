package practice.algorithm.code.travelling_salesman.data;

public class City {

	private static final double EARTH_EQUATORIAL_RADIUS = 6378.1370D;
	private static final double DEGREES_TO_RADIANS = Math.PI/180D;
	
	private final double longitude;
	private final double latitude;
	private final String name;
	
	public City(double longitude, double latitude, String name){
		this.longitude = longitude * DEGREES_TO_RADIANS;
		this.latitude = latitude * DEGREES_TO_RADIANS;
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getName() {
		return name;
	}	
	
	public double measureDistance(City city) {
		double deltaLongitude = city.getLongitude() - this.getLongitude();
		double deltaLatitude = city.getLatitude() - this.getLatitude();
		
		double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D)
				+ Math.cos(this.getLatitude()) * Math.cos(city.getLatitude()) * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
		return Math.abs(2D * EARTH_EQUATORIAL_RADIUS * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a)));
	}
	
	public String toString(){
		return name;
	}
}
