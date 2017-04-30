package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

/**
 * 
 * @author rajeshnailwal
 * 
 * Best-first search is a search algorithm which explores a graph by expanding the most promising node chosen according to a specified rule.
 * 
 *
 */
public class PathInGraphUsingBestFirstSearch {
	
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
				
				//get the node among the adjacent nodes with smallest distance from destination
				if(adjacentNodes != null && adjacentNodes.size() > 0){
					node = adjacentNodes.get(0);
					
					for(GraphNode nd : adjacentNodes){
						if(((Integer)node.additionalInfo).intValue() >= ((Integer)nd.additionalInfo).intValue()){
							node = nd;
						}
					}
					
					stack.push(node);
				} else {
					//If there is no adjacent node it means the path till this node doesn't reach destination.
					//hence we adjust a little and find next best optimized node in the path find node which 
					//is next smallest to 'node' as no path exists from 'node' due to empty adjacent nodes
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
