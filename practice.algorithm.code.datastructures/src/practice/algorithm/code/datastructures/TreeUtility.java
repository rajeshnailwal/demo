package practice.algorithm.code.datastructures;

import practice.algorithm.code.datastructures.data.TreeNode;

public class TreeUtility {
	
	public static void preOrderTraversal(TreeNode node){
		if(node != null) {
			preorder(node, 0);
		}
	}
	
	private static void preorder(TreeNode node, int level) {
		if(node != null){
			System.out.println("{"+node + ", Level "+level+"}");
			preorder(node.left, level + 1);
			preorder(node.right, level + 1);
		}
	}
	
	public static void postOrderTraversal(TreeNode node){
		if(node != null) {
			postorder(node, 0);
		}
	}
	
	private static void postorder(TreeNode node, int level) {
		if(node != null){
			postorder(node.left, level + 1);
			postorder(node.right, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
		}
	}
	
	public static void inOrderTraversal(TreeNode node){
		if(node != null) {
			inorder(node, 0);
		}
	}
	
	private static void inorder(TreeNode node, int level) {
		if(node != null){
			inorder(node.left, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
			inorder(node.right, level + 1);
		}
	}
	
}
