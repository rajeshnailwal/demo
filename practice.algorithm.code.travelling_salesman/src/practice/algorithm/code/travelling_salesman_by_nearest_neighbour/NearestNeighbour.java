package practice.algorithm.code.travelling_salesman_by_nearest_neighbour;

import java.util.ArrayList;
import java.util.List;

import practice.algorithm.code.travelling_salesman.data.City;
import practice.algorithm.code.travelling_salesman.data.Route;

public class NearestNeighbour {
	
	public Route getShortestRoute(List<City> cities){
		List<City> shortestPath = new ArrayList<City>();
		
		City city = cities.get((int)(cities.size() * Math.random()));
		cities.remove(city);
		shortestPath.add(city);
		
		while(cities.size() > 0){
			city = getNearestNeighbour(cities, city);
			cities.remove(city);
			shortestPath.add(city);
		}
		
		return new Route(shortestPath);
	}
	
	public City getNearestNeighbour(List<City> cities, City city) {
		return cities.stream().min((city1, city2) -> {
			int val = 0;
			
			if(city.measureDistance(city1) < city.measureDistance(city2)) val = -1;
			else if (city.measureDistance(city1) > city.measureDistance(city2)) val = 1;
			
			return val;
		}).get();
	}
	
}
