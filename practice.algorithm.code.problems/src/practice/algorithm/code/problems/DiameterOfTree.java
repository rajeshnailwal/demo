package practice.algorithm.code.problems;

import java.util.Stack;

/**
 * 
 * @author rajeshnailwal
 * 
 * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path 
 * between two leaves in the tree. The diagram below shows two trees each with diameter nine, the 
 * leaves that form the ends of a longest path are shaded (note that there is more than one path 
 * in each tree of length nine, but no path longer than nine nodes)
 * 
 * The diameter of a tree T is the largest of the following quantities:
 * the diameter of T’s left subtree
 * the diameter of T’s right subtree
 * the longest path between leaves that goes through the root of T (this can be computed from the 
 * heights of the subtrees of T)
 *
 */
public class DiameterOfTree {
	
	private static Node root;
	
	public static void main(String...strings){
		//makeTree(new int[]{30,10,37,5,50,34,67,11,19,8,77,91});
		//makeTree(new int[]{50,30,32,35,33,40,45,15,25,20,18,22,5});
		root = makeTree(new int[]{100,50,150,40,60,70,55,150,200,300,400,250,225,275});
		
		DiameterRoot diameterRoot = new DiameterRoot();
		
		getDiameter(root, diameterRoot);
		System.out.println(diameterRoot.diameter+"==="+diameterRoot.node);		
		
	}
	
	public static int getDiameter(Node node, DiameterRoot diaRoot) {
		int height = -1;
		if(node != null){
			int leftHeight = getDiameter(node.left, diaRoot);
			int rightHeight = getDiameter(node.right, diaRoot);
			
			if(leftHeight >= rightHeight) height = leftHeight + 1;
			else height = rightHeight + 1;
			
			int localDiameter = (leftHeight + 1) + (rightHeight + 1) + 1;
			
			if(diaRoot.diameter < localDiameter) {
				diaRoot.diameter = localDiameter;
				diaRoot.node = node;
			}
			
		}
		return height;
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
	
	private static class DiameterRoot {
		Node node;
		int diameter;
	}
	
}
