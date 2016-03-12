package com.nailwal.rajesh.tree;

import com.nailwal.rajesh.data.TreeNode;

public class TreeTraversal {

	/*
	 * left - parent - right
	 * 
	 */
	public static void inOrder(TreeNode<?> node){
		if(node != null){
			inOrder(node.left);
			System.out.print(node.data +" ");
			inOrder(node.right);
		}
	}
	
	/*
	 * parent - left - right
	 */
	public static void preOrder(TreeNode<?> node){
		if(node != null){
			System.out.print(node.data+" ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	/*
	 * left - right - parent
	 */
	public static void postOrder(TreeNode<?> node){
		if(node != null){
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data+" ");
		}
	}

}
