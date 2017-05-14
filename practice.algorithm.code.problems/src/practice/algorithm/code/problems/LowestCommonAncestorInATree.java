package practice.algorithm.code.problems;

import java.util.Stack;

public class LowestCommonAncestorInATree {

	private static Node root;
	
	public static void main(String[] args) {
		Node[] nodes = {
							new Node(34), new Node(45), new Node(83), new Node(18),
							new Node(90), new Node(12), new Node(123), new Node(7),
							new Node(38), new Node(1234), new Node(71), new Node(70),
							new Node(88), new Node(10), new Node(32), new Node(76),
							new Node(31), new Node(23), new Node(84), new Node(44)
						};
		makeTreeFromArray(nodes);
		
		Node first = nodes[(int)(Math.random() * nodes.length)];
		Node second = nodes[(int)(Math.random() * nodes.length)];
		
		System.out.println(first);
		System.out.println(second);
		
		Stack st1 = new Stack();
		Stack st2 = new Stack();
		
		Node node = first;
		while(node != null) {
			st1.push(node);
			node = node.parent;
		}
		
		node = second;
		while(node != null) {
			st2.push(node);
			node = node.parent;
		}
		
		Node lastCommonPop = null;
		
		while(!st1.isEmpty() && !st2.isEmpty()){
			Node pop1 = (Node)st1.pop();
			Node pop2 = (Node)st2.pop();
			
			if(pop1 == pop2){
				lastCommonPop = pop1;
			} else {
				break;
			}
		}
		
		System.out.println(lastCommonPop);
	}
	
	private static void makeTreeFromArray(Node[] nodes) {
		int i = 0;
		
		root = nodes[i];
		
		while(i <= (((nodes.length - 1) / 2))) {
			int leftChild = 2 * i + 1;
			int rightChild = 2 * i + 2;
			nodes[i].left = nodes[leftChild];
			nodes[leftChild].parent = nodes[i];
			
			if(rightChild < nodes.length - 1) {
				nodes[i].right = nodes[rightChild];
				nodes[rightChild].parent = nodes[i];
			}
			++i;
		}
	}
	
	
	private static class Node {
		int data;
		int height;
		
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

}
