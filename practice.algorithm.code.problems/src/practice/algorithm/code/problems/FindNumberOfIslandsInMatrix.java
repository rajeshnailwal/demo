package practice.algorithm.code.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rajeshnailwal
 * 
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. 
 * For example, the below matrix contains 5 islands. 
 * This is an variation of the standard problem: “Counting number of connected components in a undirected graph”
 *
 */
public class FindNumberOfIslandsInMatrix {
	
	private static Node[][] matrix;
	
	public static void main(String...strings) {
		
		/*
		 * {1, 1, 0, 0, 0}
         * {0, 1, 0, 0, 1}
         * {1, 0, 0, 1, 1}
         * {0, 0, 0, 0, 0}
         * {1, 0, 1, 0, 1}
		 */
		matrix = new Node[][]{
							{new Node(1,0,0),new Node(1,0,1),new Node(0,0,2),new Node(0,0,3),new Node(0,0,4)},
							{new Node(0,1,0),new Node(1,1,1),new Node(0,1,2),new Node(0,1,3),new Node(1,1,4)},
							{new Node(1,2,0),new Node(0,2,1),new Node(0,2,2),new Node(1,2,3),new Node(1,2,4)},
							{new Node(0,3,0),new Node(0,3,1),new Node(0,3,2),new Node(0,3,3),new Node(0,3,4)},
							{new Node(1,4,0),new Node(0,4,1),new Node(1,4,2),new Node(0,4,3),new Node(1,4,4)}
						 };
		
		int numberOfIsland = 0;
		
		for(int i = 0; i < matrix.length; ++i){
			for(int j = 0; j < matrix[i].length; ++j) {
				Node node = matrix[i][j];
				if(node.data == 1 && !node.isVisited){
					++numberOfIsland;
					makeAdjacencyList(node);
				}
			}
		}
		
		System.out.println(numberOfIsland);
	}
	
	private static void makeAdjacencyList(Node node){
		int i = node.positionX;
		int j = node.positionY;
		
		node.isVisited = true;
		
		Node adjacentNode = getAdjacentNode(i + 1, j + 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i + 1, j - 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i - 1, j + 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i - 1, j - 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i, j + 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i, j - 1);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i + 1, j);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		adjacentNode = getAdjacentNode(i - 1, j);
		if(adjacentNode != null){
			node.adjacencyList.add(adjacentNode); 
		}
		
		if(!node.adjacencyList.isEmpty()){
			for(int index = 0; index < node.adjacencyList.size(); ++index){
				adjacentNode = node.adjacencyList.get(index);
				if(!adjacentNode.isVisited){
					makeAdjacencyList(node.adjacencyList.get(index));
				}
			}
		}
		
	}
	
	public static Node getAdjacentNode(int positionX, int positionY){
		Node node = null;
		
		if(positionX > -1 && positionX < 5 && positionY > -1 && positionY < 5) {
			node = matrix[positionX][positionY];
			
			if(node.data == 0 || node.isVisited){
				node = null;
			}
			/*if(node.data == 1 && !node.isVisited){
				//node.isVisited = true;
			} else if(node.data == 0){
				node = null;
			}*/
			
		}
				
		return node;
	}
	
	public static class Node {
		int data;
		int positionX;
		int positionY;
		boolean isVisited;
		
		public Node(int data, int positionX, int positionY) {
			this.data = data;
			this.positionX = positionX;
			this.positionY = positionY;
		}
		
		List<Node> adjacencyList = new ArrayList<Node>();
		
		public String toString(){
			return data+" = ("+positionX+","+positionY+")";
		}
	}
	
	
	
}
