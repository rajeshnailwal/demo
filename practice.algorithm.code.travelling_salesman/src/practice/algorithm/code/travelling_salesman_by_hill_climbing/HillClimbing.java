package practice.algorithm.code.travelling_salesman_by_hill_climbing;

import practice.algorithm.code.travelling_salesman.data.Route;

public class HillClimbing {
	
	private static final int ATTEMPTS = 100;
	
	public Route getShortestRoute(Route currentRoute){
		
		for(int i = 0; i < ATTEMPTS; ++i){
			
			Route alternativeRoute = getAlternativeRoute(currentRoute);
			
			if(currentRoute.getTotalDistance() > alternativeRoute.getTotalDistance()){
				currentRoute = alternativeRoute;
			}		
			
			System.out.println("Distance "+currentRoute.getTotalDistance() +", Route "+currentRoute);
		}
		return currentRoute;
	}
	
	private Route getAlternativeRoute(Route currentRoute){
		int index1 = 0, index2 = 0;
		
		Route alternativeRoute = new Route(currentRoute);
		
		while(index1 == index2) {
			index1 = (int)(currentRoute.getCities().size() * Math.random());
			index2 = (int)(currentRoute.getCities().size() * Math.random());
		}
		
		alternativeRoute.getCities().set(index1, currentRoute.getCities().get(index2));
		alternativeRoute.getCities().set(index2, currentRoute.getCities().get(index1));
		
		return alternativeRoute;
	}
	
}
