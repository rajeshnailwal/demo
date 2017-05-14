package practice.algorithm.code.problems;

import java.util.Stack;

public class PrintCommonNodesInTwoBST {
	
	public static void main(String...strings) {
		
		Node root1 = makeTree(new int[]{30,6,73,5,4,43,67,1,9,8,70,99});
		Node root2 = makeTree(new int[]{30,10,37,5,50,34,67,11,19,8,77,91});
		
		Stack stack1 = new Stack();
		Stack stack2 = new Stack();
		
		inorder(stack1, root1);
		inorder(stack2, root2);
		
		if(!stack1.isEmpty() && !stack2.isEmpty()) {
			Node node1 = (Node)stack1.pop();
			Node node2 = (Node)stack2.pop();
			
			if(node1 != null && node2 != null) {
				//Check for same node till either of the stack gets empty 
				do {
					if(node1.data == node2.data) {
						System.out.println(node1);
						node1 = (Node)stack1.pop();
						node2 = (Node)stack2.pop();
					} else if(node1.data > node2.data) {
						node1 = (Node)stack1.pop();
					} else {
						node2 = (Node)stack2.pop();
					}
				}while(!stack1.isEmpty() && !stack2.isEmpty());
				
				//check rest of the non-empty stack for boundary condition
				while(stack1.isEmpty() && !stack2.isEmpty()) {
					if(node1.data == node2.data){
						System.out.println(node1);
						break;
					} else if(node1.data > node2.data){
						break;
					} else {
						node2 = (Node)stack2.pop();
					}
				}
				
				while(!stack1.isEmpty() && stack2.isEmpty()) {
					if(node1.data == node2.data){
						System.out.println(node1);
						break;
					} else if(node1.data > node2.data){
						node1 = (Node)stack1.pop();
					} else {
						break;
					}
				}
			}
		}
		
	}
	
	
	public static Node makeTree(int[] arr1) {
		Node root = null;
		for(int data : arr1){
			root = insert(root, data);
		}
		return root;
	}
	
	public static Node insert(Node root, int data) {
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
	
	private static void inorder(Stack stack, Node node){
		if(node != null){
			inorder(stack, node.left);
			stack.push(node);
			inorder(stack, node.right);
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
}
