package practice.algorithm.code.travelling_salesman_by_hill_climbing;

import practice.algorithm.code.travelling_salesman.data.Data;
import practice.algorithm.code.travelling_salesman.data.Route;

public class TravellingSalesmanByHillClimbing {
	
	public static void main(String...strings){		
		
		Route route = new HillClimbing().getShortestRoute(new Route(Data.getData()));
		
	}
}
