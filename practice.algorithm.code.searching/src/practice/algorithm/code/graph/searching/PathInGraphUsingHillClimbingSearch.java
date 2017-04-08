package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Stack;import java.util.stream.Collector;
import java.util.stream.Collectors;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

/**
 * 
 * @author rajesh nailwal
 * 
 * In simple hill climbing, the first closer node is chosen, whereas in steepest ascent hill climbing all successors are compared 
 * and the closest to the solution is chosen. Both forms fail if there is no closer node, which may happen if there are local maxima 
 * in the search space which are not solutions.
 */

public class PathInGraphUsingHillClimbingSearch {

	public static void main(String[] args) {
		List<GraphNode> graph = new Graph().getDummyGraphWithDistancesFomEachNodeToDestination_L();
		System.out.println(graph);
		
		List<GraphNode> path = search(graph, "L");
		
		System.out.println(path);
	}
	
	public static List<GraphNode> search(List<GraphNode> graph, String name){
		GraphNode node = graph.get(0);
		
		Stack<GraphNode> stack = new Stack<GraphNode>();
		stack.push(node);
		
		while(stack.size() > 0 && (node = stack.peek()) != null) {
			if(node.name.equals(name)){
				return new ArrayList<GraphNode>(stack);
			} else {
				List<GraphNode> adjacentNodes = node.adjacentNodes;
				
				if(adjacentNodes != null && adjacentNodes.size() > 0){//get the node among the adjacent nodes with smallest distance from destination
					node = adjacentNodes.get(0);
					
					for(GraphNode nd : adjacentNodes){
						if(((Integer)node.additionalInfo).intValue() >= ((Integer)nd.additionalInfo).intValue()){
							node = nd;
						}
					}
					
					stack.push(node);
				} else {
					//if there is no adjacent node it means the path till this node doesn't reach destination. It is a condition of local minima/maxima.
					//hence we adjust a little and find next best optimized node in the path
					//find node which is next smallest to 'node' 
					//as no path exists from 'node' due to empty adjacent nodes
					node = stack.pop();
					GraphNode previousNodeInPath = null;
					
					while(stack.size() > 0 && (previousNodeInPath = stack.peek()) != null){
						adjacentNodes = previousNodeInPath.adjacentNodes;
						
						if(adjacentNodes != null && adjacentNodes.size() > 0){
							GraphNode gn = node;
							
							//This list only contains those nodes which are not 'gn' and whose distance from destination is not less then the destination of 'gn'
							adjacentNodes = adjacentNodes.stream().filter(n -> {
								return !n.name.equals(gn.name) && ((Integer)n.additionalInfo).intValue() >= ((Integer)gn.additionalInfo).intValue();
							}).collect(Collectors.toList());
							
							if(adjacentNodes.size() > 0){
								node = adjacentNodes.get(0);
								
								for(GraphNode nd : adjacentNodes){
									if(((Integer)node.additionalInfo).intValue() >= ((Integer)nd.additionalInfo).intValue()){
										node = nd;
									}
								}
								
								stack.push(node);
								break;
							} else {
								//If there is no next smallest node then go a level up in the graph/tree
								node = stack.pop();
							}
							
						}						
					}
				}
			}
		}
		
		return null;
	}

}
