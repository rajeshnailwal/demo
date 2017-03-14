package practice.algorithm.code.travelling_salesman_by_nearest_neighbour;

import practice.algorithm.code.travelling_salesman.data.Data;
import practice.algorithm.code.travelling_salesman.data.Route;

public class TravellingSalesmanByNearestNeighbour {
	public static void main(String...strings){
		Route route = new NearestNeighbour().getShortestRoute(Data.getData());
		System.out.println("Distance = "+route.getTotalDistance()+", route = "+route);		
	}
}
