package practice.algorithm.code.searching.data;

import java.util.ArrayList;
import java.util.List;

public class Graph<E extends Comparable<E>> {
	
	public List<GraphNode<E>> getGraph(List<GraphEdge<E>> edges){
		List<GraphNode<E>> graph = new ArrayList<GraphNode<E>>();
		
		if(edges != null && edges.size() > 0){
			edges.stream().forEach(edge -> {
				if(!graph.contains(edge.firstNode)){
					graph.add(edge.firstNode);
				}
				edge.firstNode.adjacentNodes.add(edge.secondNode);
			});
		}
		
		return graph;
	}
	
	public List<GraphNode<E>> getDummyGraph(){
		List<GraphEdge<E>> edges = new ArrayList<GraphEdge<E>>(){
			{
				GraphNode<E> nodeA = new GraphNode<E>("A", null);
				GraphNode<E> nodeB = new GraphNode<E>("B", null);
				GraphNode<E> nodeC = new GraphNode<E>("C", null);
				GraphNode<E> nodeD = new GraphNode<E>("D", null);
				GraphNode<E> nodeE = new GraphNode<E>("E", null);
				GraphNode<E> nodeF = new GraphNode<E>("F", null);
				GraphNode<E> nodeG = new GraphNode<E>("G", null);
				GraphNode<E> nodeH = new GraphNode<E>("H", null);
				GraphNode<E> nodeI = new GraphNode<E>("I", null);
				GraphNode<E> nodeJ = new GraphNode<E>("J", null);
				GraphNode<E> nodeK = new GraphNode<E>("K", null);
				GraphNode<E> nodeL = new GraphNode<E>("L", null);
				GraphNode<E> nodeM = new GraphNode<E>("M", null);
				
				add(new GraphEdge<E>(nodeA, nodeB, 8));
				add(new GraphEdge<E>(nodeA, nodeC, 7));
				add(new GraphEdge<E>(nodeA, nodeD, 6));
				add(new GraphEdge<E>(nodeB, nodeC, 5));
				add(new GraphEdge<E>(nodeB, nodeF, 5));
				add(new GraphEdge<E>(nodeB, nodeE, 5));
				add(new GraphEdge<E>(nodeC, nodeJ, 9));
				add(new GraphEdge<E>(nodeC, nodeD, 4));
				add(new GraphEdge<E>(nodeD, nodeG, 3));
				add(new GraphEdge<E>(nodeD, nodeH, 2));
				add(new GraphEdge<E>(nodeF, nodeI, 4));
				add(new GraphEdge<E>(nodeF, nodeJ, 7));
				add(new GraphEdge<E>(nodeE, nodeJ, 3));
				add(new GraphEdge<E>(nodeG, nodeJ, 8));
				add(new GraphEdge<E>(nodeG, nodeK, 9));
				add(new GraphEdge<E>(nodeH, nodeG, 1));
				add(new GraphEdge<E>(nodeJ, nodeK, 1));
				add(new GraphEdge<E>(nodeJ, nodeI, 2));
				add(new GraphEdge<E>(nodeI, nodeL, 1));
				add(new GraphEdge<E>(nodeM, nodeL, 6));
				add(new GraphEdge<E>(nodeK, nodeM, 5));
			}
		};
		
		return getGraph(edges);
	}
}
