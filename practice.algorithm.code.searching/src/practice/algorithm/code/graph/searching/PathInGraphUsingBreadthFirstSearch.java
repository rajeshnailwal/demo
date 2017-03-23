package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

public class PathInGraphUsingBreadthFirstSearch {
	
	public static void main(String...strings){		
		List<GraphNode<Integer>> graph = new Graph<Integer>().getDummyGraph();
		System.out.println(graph);
	}
	
	public static List<GraphNode<Integer>> search(List<GraphNode<Integer>> graph, String name){
		GraphNode<Integer> node = graph.get(0);
		
		List<GraphNode<Integer>> list = new ArrayList<GraphNode<Integer>>();
		list.add(node);
		
		if(node.name.equals(name)){
			return list;
		} else {
			Queue<List<GraphNode<Integer>>> queue = new LinkedList<List<GraphNode<Integer>>>();
			queue.offer(list);
		
			while(queue.size() > 0 && (list = queue.poll()) != null){
				
				List<GraphNode<Integer>> adjacentNodes = list.get(list.size() - 1).adjacentNodes;				
				
				if(adjacentNodes != null && adjacentNodes.size() > 0){
					for(GraphNode<Integer> gn : adjacentNodes){
						List<GraphNode<Integer>> ls = new ArrayList<GraphNode<Integer>>(list);
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
		
		return null;
	}
	
	
}
