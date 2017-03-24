package practice.algorithm.code.graph.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import practice.algorithm.code.searching.data.Graph;
import practice.algorithm.code.searching.data.GraphNode;

public class PathInGraphUsingDepthFirstSearch {
	
	public static void main(String...strings) {
		List<GraphNode> graph = new Graph().getDummyGraphWithDistancesBetweenNodes();
		System.out.println(graph);
		
		List<GraphNode> path = search(graph, "L");
		
		System.out.println(path);
	}
	
	public static List<GraphNode> search(List<GraphNode> graph, String name) {
		GraphNode node = graph.get(0);
		List<GraphNode> list = new ArrayList<GraphNode>();
		list.add(node);
		
		if(node.name.equals(name)){
			return list;
		} else {
			Stack<List<GraphNode>> stack = new Stack<List<GraphNode>>();
			stack.push(list);
			
			while(stack.size() > 0 && (list = stack.pop()) != null){
				node = list.get(list.size() - 1);
				List<GraphNode> adjacentNodes = node.adjacentNodes;
				
				if(adjacentNodes != null && adjacentNodes.size() > 0){
					for(int i = 0; i< adjacentNodes.size(); ++i){
						List<GraphNode> ls = new ArrayList<GraphNode>(list);
						
						GraphNode nd = adjacentNodes.get(adjacentNodes.size() - 1 - i);
						
						if(!ls.contains(nd)){//this node is not traversed twice
							
							ls.add(nd);
							
							if(nd.name.equals(name)){
								return ls;
							} else {
								stack.push(ls);
							}
						}
						
					}
				}
			}
		}
		
		return null;
	}
}
