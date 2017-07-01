package practice.algorithm.code.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class VerticalTraversalInTree {
	
	private static Node root;
	
	public static void main(String...strings) {
		root = makeTree(new int[]{100,50,150,40,60,70,55,200,300,400,250,225,275});
		
		HashMap<Integer, ArrayList<Node>> map = new HashMap<Integer, ArrayList<Node>>();
		int relativeVerticalPosition = 0;
		preorder(map, root, relativeVerticalPosition);
		
		int smallest = Integer.MAX_VALUE, largest = Integer.MIN_VALUE, i = 0;
		
		Iterator<Integer> iterator = map.keySet().iterator();
		
		while(iterator.hasNext()){
			Integer key = iterator.next();
			if(key < smallest) smallest = key;
			if(key > largest) largest = key;
		}
		
		for(int key = smallest; key <= largest; ++key){
			List<Node> ls = map.get(key);
			for(int val = 0; val < ls.size(); ++val){
				System.out.print(ls.get(val).data+" ");
			}
			System.out.println();
		}
		
	}
	
	private static Node makeTree(int[] arr1) {
		Node root = null;
		for(int data : arr1){
			root = insert(root, data);
		}
		return root;
	}
	
	private static Node insert(Node root, int data) {
		if(root == null) {
			root = new Node(data);
		} else {
			Node node = root;
			Node parent = null;
			
			while(node != null && node.data != data){
				parent = node;
				if(data < node.data){
					node = node.left;
				} else {
					node = node.right;
				}
			}
			
			if(parent != null) {
				node = new Node(data);
				if(data < parent.data) parent.left = node;
				else parent.right = node;
				node.parent = parent;
			}
		}
		return root;
	}
	
	private static void preorder(HashMap<Integer, ArrayList<Node>> map, Node node, int relativeVerticalPosition){
		if(node != null){
			ArrayList<Node> list = map.get(relativeVerticalPosition);
			if(list == null){
				list = new ArrayList<Node>();
				map.put(relativeVerticalPosition, list);
			}
			list.add(node);
			preorder(map, node.left, relativeVerticalPosition - 1);
			preorder(map, node.right, relativeVerticalPosition + 1);
		}
	}
	
	private static class Node {
		int data;
		
		public Node(int data){
			this.data = data;
		}
		
		Node left;
		Node right;
		Node parent;
		
		public String toString(){
			return (left != null ? Integer.toString(left.data) : "null") + " <- " + data +" -> "+(right != null ? Integer.toString(right.data) : "null") +", parent "+(parent != null ? Integer.toString(parent.data) : "null");
		}
	}
	
	private static class VerticalPosition {
		Node node;
		int diameter;
	}
}