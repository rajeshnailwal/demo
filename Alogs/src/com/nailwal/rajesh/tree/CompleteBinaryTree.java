package com.nailwal.rajesh.tree;

import java.util.ArrayList;
import java.util.List;

import com.nailwal.rajesh.data.Data;
import com.nailwal.rajesh.data.TreeNode;

public class CompleteBinaryTree {
	
	/*
	 * Always remember array is the best way to implement a binary tree
	 * 
	 * A node at 2*x location always has a child at 2*x + 1 and 2*x + 2
	 * where x is the index in array and represents the position of node
	 * in binary tree (while moving left to right on each level on starting
	 * from root to leaves)
	 * 
	 * Similarly to find a parent of a node (x - 1)/2 will give the parent of node
	 * at index x
	 * 
	 * Use a dynamic array or a arraylist
	 * 
	 */
	
	private static List<TreeNode> array = new ArrayList<TreeNode>();
	
	public static void main(String[] args) {
		/*
		 * This creates a complete binary tree with 30 nodes
		 */
		for(int i = 0; i < 30; ++i){
			add(array, new TreeNode(new Data<Integer>(Integer.valueOf(i))));
		}
		
		TreeTraversal.postOrder(array.get(0));
		
	}
	
	/*
	 * . add new tree node at the end of array
	 * . get the position of its parent by calculating 
	 *   (x - 1) / 2 where x is the position of this node
	 * . get parent
	 * . set child of parent (first add left child if it
	 *   doesn't exist else add right child) 
	 */
	public static void add(List<TreeNode> array, TreeNode newNode){
		if(array == null){
			array = new ArrayList<TreeNode>();
		} 
		
		if(array.size() == 0){
			array.add(0, newNode);
		} else {
			int nodePosition = array.size();
			int parentPosition = (nodePosition - 1) / 2;
			
			array.add(array.size(), newNode);
			
			TreeNode parent = array.get(parentPosition);
			
			if(parent.left == null){
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
			
		}
	}
	
	private static void verifyHierarchy(List<TreeNode> array){
		for(int i = 0; i < array.size(); ++i){
			TreeNode node = array.get(i);
			TreeNode leftChild = node.left;
			TreeNode rightChild = node.right;
		
			System.out.println("parent "+node.data);
		
			if(leftChild != null){
				System.out.print("left child "+array.get(i).left.data);
			} 
		
			if(rightChild != null){
				System.out.print("---right child "+array.get(i).right.data);
			}
		
			System.out.println("=============");
		}
	}
}
