package practice.algorithm.code.searching.data;

public class GraphEdge<E extends Comparable<E>>  {
	
	public GraphNode<E> firstNode;
	public GraphNode<E> secondNode;
	public int weight;
	
	public GraphEdge(GraphNode<E> firstNode, GraphNode<E> secondNode, int weight){
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.weight = weight;
	}
}
