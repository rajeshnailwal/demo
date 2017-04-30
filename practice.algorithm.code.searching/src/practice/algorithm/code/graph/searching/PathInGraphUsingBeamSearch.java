package practice.algorithm.code.graph.searching;

import java.util.List;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

public class PathInGraphUsingBeamSearch {
	
	public static void main(String[] args) {
		List<GraphNode> graph = new Graph().getDummyGraphWithDistancesFomEachNodeToDestination_L();
		System.out.println(graph);
		
		List<GraphNode> path = search(graph, "L");
		
		System.out.println(path);
	}
	
	public static List<GraphNode> search(List<GraphNode> graph, String name){
		return null;
	}

}
