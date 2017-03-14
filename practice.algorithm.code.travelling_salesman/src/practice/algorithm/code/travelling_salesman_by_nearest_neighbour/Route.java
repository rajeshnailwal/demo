package practice.algorithm.code.travelling_salesman_by_nearest_neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Route {
	
	private List<City> cities = new ArrayList<City>();
	
	public Route(Route route){
		cities.addAll(route.getCities());
	}
	
	public Route(List<City> cities){
		this.cities.addAll(cities);
		Collections.shuffle(this.cities);
	}
	
	public List<City> getCities(){
		return this.cities;
	}
	
	public double getTotalDistance(){
		return this.cities.stream().mapToDouble(city -> {
			int i = this.cities.indexOf(city);
			return i < this.cities.size() - 1 ? city.measureDistance(this.cities.get(i+1)) : 0;
		}).sum()
		+
		//returning to first city
		this.cities.get(this.cities.size() - 1).measureDistance(this.cities.get(0));
	}
	
	public String toString(){
		return Arrays.toString(this.cities.toArray());
	}
}
