package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

/**
 * 
 * @author rajeshnailwal
 * 
 * In simple hill climbing, the first closer node is chosen, whereas in steepest ascent hill climbing all successors are compared 
 * and the closest to the solution is chosen. Steepest hill climbing is same as that of BestFirstSearch. Both forms fail if there is no closer node, 
 * which may happen if there are local maxima in the search space which are not solutions.
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
				
				//get the node among the adjacent nodes which is smaller to 'node'
				if(adjacentNodes != null && adjacentNodes.size() > 0){
					
					for(GraphNode nd : adjacentNodes){
						if(!node.name.equals(nd.name) && ((Integer)node.additionalInfo).intValue() >= ((Integer)nd.additionalInfo).intValue()){
							node = nd;
							break;
						}
					}
					
					stack.push(node);
				} else {
					//if there is no adjacent node it means the path till this node doesn't reach destination. It is a condition of local minima/maxima.
					//hence we adjust a little and find next smaller node in the path 
					//find node which is next to 'node' and smaller to 'previousNodeInPath' as no path exists from 'node' due to empty adjacent nodes
					node = stack.pop();
					GraphNode previousNodeInPath = null;
					
					while(stack.size() > 0 && (previousNodeInPath = stack.peek()) != null){
						adjacentNodes = previousNodeInPath.adjacentNodes;
						
						GraphNode gn = null;
							
						for(GraphNode nd : adjacentNodes){
							if(!node.name.equals(nd.name) && ((Integer)previousNodeInPath.additionalInfo).intValue() >= ((Integer)nd.additionalInfo).intValue()){
								gn = nd;
								break;
							}
						}
							
						if(gn != null){
							stack.push(gn);
							break;
						} else {
							//If there is no next smallest node then go a level up in the graph/tree
							node = stack.pop();
						}						
					}
				}
			}
		}
		
		return null;
	}

}
