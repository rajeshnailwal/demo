package practice.algorithm.code.datastructures;

import java.util.Random;

import practice.algorithm.code.datastructures.data.AVLTreeNode;
import practice.algorithm.code.datastructures.data.Data;
import practice.algorithm.code.datastructures.data.TreeNode;

public class BinarySearchTree {
	
	private TreeNode root;
	private static long seed = 678546;
	
	public static void main(String...strings){
		Random rand = new Random(seed);
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < 20; ++i){
			int data = (int)(1000 * rand.nextDouble());
			tree.insert(new Data(data));
			System.out.print(data+", ");
		}
		
		TreeUtility.preOrderTraversal(tree.getRoot());
		
		System.out.println("==============Deletion starting=================");
		
		tree.delete(new Data(199));
		
		TreeUtility.preOrderTraversal(tree.getRoot());
	}
	
	public TreeNode getRoot(){
		return root;
	}
	
	public TreeNode insert(Data data){
		
		TreeNode newNode = new TreeNode(data);
		
		if(root == null){
			root = newNode;
		} else {
			
			TreeNode node = root;
			TreeNode parent = null;
			
			while(node != null){
				if(data.getData().compareTo(node.data.getData()) < 0){
					parent = node;
					node = node.left;
				} else if (data.getData().compareTo(node.data.getData()) > 0) {
					parent = node;
					node = node.right;
				} else {
					return node;
				}
			}
			
			if(data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
			newNode.parent = parent;
		}
		
		return newNode;
	}
	
	public TreeNode delete(Data data) {
		TreeNode node = root;
		TreeNode parent = null;
		
		while(node != null){
			if(data.getData().compareTo(node.data.getData()) < 0){
				parent = node;
				node = node.left;
			} else if (data.getData().compareTo(node.data.getData()) > 0) {
				parent = node;
				node = node.right;
			} else {
				
				if(node.left != null && node.right != null) {
					//get inorder successor of node
					TreeNode successor = getInorderSuccessor(node.right);
					
					//copy successor data into node
					node.data = successor.data;
					
					node = successor;
				}
				
				if(node.left == null && node.right == null){
					parent = node.parent;
					
					if(parent != null){
						//disconnect node from parent
						if(parent.left == node) parent.left = null;
						else parent.right = null;
						
						//disconnect parent from node
						node.parent = null;
					} else {
						root = null;
					}
				} else if(node.left != null && node.right == null){
					parent = node.parent;
					
					if(parent != null){
						//disconnect node from parent and connect child of node with parent
						if(parent.left == node) parent.left = node.left;
						else parent.right = node.left;
						
						//connect node's parent with node's child
						node.left.parent = parent;
						
						//disconnect node from parent and from its child
						node.left = null;
						node.parent = null;
					} else {
						//make left child as new root 
						root = node.left;
						
						//disconnect child from node and disconnect node from child
						node.left.parent = null;
						node.left = null;					
					}
				} else if(node.left == null && node.right != null){
					parent = node.parent;
					
					if(parent != null){
						//disconnect node from parent and connect child of node with parent
						if(parent.left == node) parent.left = node.right;
						else parent.right = node.right;
						
						//connect node's parent with node's child
						node.right.parent = parent;
						
						//disconnect node from parent and from its child
						node.right = null;
						node.parent = null;
					} else {
						//make right child as new root 
						root = node.right;
						
						//disconnect child from node and disconnect node from child
						node.right.parent = null;
						node.right = null;						
					}
				}			
				
				break;
			}
		}
		
		return root;
	}
	
	private TreeNode getInorderSuccessor(TreeNode currentRoot){
		TreeNode node = currentRoot;
		
		while(node != null && node.left != null){
			node = node.left;
		}
		
		return node;
	}
	
}
