package practice.algorithm.code.travelling_salesman.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
	
	public static List<City> getData(){
		List<City> cities = new ArrayList<City>(Arrays.asList(
				new City(42.3601, -71.0589, "Boston"),
				new City(29.7604, -95.3698, "Houston"),
				new City(30.2672, -97.7431, "Austin"),
				new City(37.7749, -122.4194, "San Francisco"),
				new City(39.7392, -104.9903, "Denver"),
				new City(34.0522, -118.2437, "Los Angeles"),
				new City(41.8781, -87.6298, "Chicago"),
				new City(40.7128, -74.0059, "New York"),
				new City(32.7767, -96.7970, "Dallas"),
				new City(47.6062, -122.3321, "Seattle")));
		return cities;
	}

}
