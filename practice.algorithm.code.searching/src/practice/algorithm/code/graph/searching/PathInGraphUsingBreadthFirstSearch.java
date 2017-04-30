package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

public class PathInGraphUsingBreadthFirstSearch {
	
	public static void main(String...strings){		
		List<GraphNode> graph = new Graph().getDummyGraphWithDistancesBetweenNodes();
		System.out.println(graph);
		
		List<GraphNode> path = search(graph, "L");
		
		System.out.println(path);
	}
	
	public static List<GraphNode> search(List<GraphNode> graph, String name){
		GraphNode<Integer> node = graph.get(0);
		
		List<GraphNode> list = new ArrayList<GraphNode>();
		list.add(node);
		
		if(node.name.equals(name)){
			return list;
		} else {
			Queue<List<GraphNode>> queue = new LinkedList<List<GraphNode>>();
			queue.offer(list);
		
			while(queue.size() > 0 && (list = queue.poll()) != null){
				
				List<GraphNode> adjacentNodes = list.get(list.size() - 1).adjacentNodes;				
				
				if(adjacentNodes != null && adjacentNodes.size() > 0){
					for(GraphNode gn : adjacentNodes){
						List<GraphNode> ls = new ArrayList<GraphNode>(list);
						
						if(!ls.contains(gn)){
							
							ls.add(gn);
							
							if(gn.name.equals(name)){
								return ls;
							} else {
								queue.offer(ls);
							}
						}
					}	
				}
				
				
			}
			
		}
		
		return null;
	}
	
	
}
