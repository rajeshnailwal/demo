package practice.algorithm.code.datastructures;

import java.util.Random;

import practice.algorithm.code.datastructures.data.AVLTreeNode;
import practice.algorithm.code.datastructures.data.Data;

public class AVLTree {
	
	private AVLTreeNode root;
	private static long seed = 678546;
	
	public static void main(String...strings){
		Random rand = new Random(seed);
		AVLTree tree = new AVLTree();
		for(int i = 0; i < 20; ++i){
			int data = (int)(1000 * rand.nextDouble());
			tree.insert(new Data(data));
		}
		
		tree.preOrderTraversal(tree.getRoot());
		
		System.out.println("==============Deletion starting=================");
		
		tree.delete(new Data(666));
		tree.delete(new Data(780));
		tree.delete(new Data(721));
		
		tree.preOrderTraversal(tree.getRoot());
	}
	
	public AVLTreeNode getRoot(){
		return root;
	}
	
	public AVLTreeNode insert(Data data){
		
		AVLTreeNode newNode = new AVLTreeNode(data);
		
		if(root == null){
			root = newNode;
		} else {
			
			AVLTreeNode node = root;
			AVLTreeNode parent = null;
			
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
		
		AVLTreeNode node = newNode;
		
		int leftHeight = 0, rightHeight = 0, balanceFactor = 0;
		while(node != null){
			leftHeight = node.left != null ? node.left.height : -1;
			rightHeight = node.right != null ? node.right.height : -1;
			node.height = Math.max(leftHeight, rightHeight) + 1;
			
			balanceFactor = leftHeight - rightHeight;
			
			if(balanceFactor <= -2){//right side is heavy
				AVLTreeNode rightChild = node.right;
				if(newNode.data.getData().compareTo(rightChild.data.getData()) < 0) {//right-left case
					node = rightLeftCase(node);					
				} else if(newNode.data.getData().compareTo(rightChild.data.getData()) > 0) {//right-right case
					node = rightRightCase(node);
				}
			} else if(balanceFactor >= 2){//left side is heavy
				AVLTreeNode leftChild = node.left;
				if(newNode.data.getData().compareTo(leftChild.data.getData()) < 0) {//left-left case
					node = leftLeftCase(node);
				} else if(newNode.data.getData().compareTo(leftChild.data.getData()) > 0) {//left-right case
					node = leftRightCase(node);					
				}
			} else {
				node = node.parent;
			}
			
		}
		
		return getRoot();
	}
	
	public AVLTreeNode delete(Data data) {
		AVLTreeNode node = root;
		AVLTreeNode parent = null;
		AVLTreeNode nodeForBalancing = null;
		
		while(node != null){
			if(data.getData().compareTo(node.data.getData()) < 0){
				parent = node;
				node = node.left;
			} else if (data.getData().compareTo(node.data.getData()) > 0) {
				parent = node;
				node = node.right;
			} else {//node found
				
				if(node.left != null && node.right != null){
					//get inorder successor of node
					AVLTreeNode successor = getInorderSuccessor(node.right);
					
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
						
						nodeForBalancing = parent;
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
						
						nodeForBalancing = parent;
					} else {
						//make left child as new root 
						root = node.left;
						
						//disconnect child from node and disconnect node from child
						node.left.parent = null;
						node.left = null;
						
						nodeForBalancing = root;						
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
						
						nodeForBalancing = parent;
					} else {
						//make right child as new root 
						root = node.right;
						
						//disconnect child from node and disconnect node from child
						node.right.parent = null;
						node.right = null;
						
						nodeForBalancing = root;						
					}
				}				
				
				break;
			}
		}
		
		node = nodeForBalancing;
		
		int leftHeight = 0, rightHeight = 0, balanceFactor = 0;
		while(node != null){
			leftHeight = node.left != null ? node.left.height : -1;
			rightHeight = node.right != null ? node.right.height : -1;
			node.height = Math.max(leftHeight, rightHeight) + 1;
			
			balanceFactor = leftHeight - rightHeight;
			
			if(balanceFactor <= -2){//right side is heavy
				AVLTreeNode rightChild = node.right;
				leftHeight = rightChild.left != null ? rightChild.left.height : -1;
				rightHeight = rightChild.right != null ? rightChild.right.height : -1;
				
				if(leftHeight - rightHeight <= 0){//right-right case
					node = rightRightCase(node);	
				} else {//right-left case
					node = rightLeftCase(node);	
				}
			} else if(balanceFactor >= 2){//left side is heavy
				AVLTreeNode leftChild = node.left;
				leftHeight = leftChild.left != null ? leftChild.left.height : -1;
				rightHeight = leftChild.right != null ? leftChild.right.height : -1;
				
				if(leftHeight - rightHeight >= 0){//left-left case
					node = leftLeftCase(node);
				} else {//left-right case
					node = leftRightCase(node);		
				}
			} else {
				node = node.parent;
			}
			
		}
		
		return root;
	}
	
	private AVLTreeNode rightLeftCase(AVLTreeNode node){
		AVLTreeNode rightChild = node.right;
		
		//Right rotate on 'rightChild'
		AVLTreeNode right_leftChild = rightChild.left;
		AVLTreeNode parent = node.parent;
		
		rightChild.left = right_leftChild.right;
		if(right_leftChild.right != null) right_leftChild.right.parent = rightChild.left;
		
		node.right = right_leftChild;
		right_leftChild.parent = node;
		
		right_leftChild.right = rightChild;
		rightChild.parent = right_leftChild;
		
		rightChild.height = Math.max(rightChild.left != null ? rightChild.left.height : -1, rightChild.right != null ? rightChild.right.height : -1) + 1;
		right_leftChild.height = Math.max(right_leftChild.left != null ? right_leftChild.left.height : -1, right_leftChild.right != null ? right_leftChild.right.height : -1) + 1;
		node.height = Math.max(node.left != null ? node.left.height : -1, node.right != null ? node.right.height : -1) + 1;
		
		
		//Left rotate on 'node'
		rightChild = right_leftChild;
		
		node.right = rightChild.left;
		if(rightChild.left != null) rightChild.left.parent = node;
							
		rightChild.left = node;
		node.parent = rightChild;
		
		if(parent != null) {
			rightChild.parent = parent;
			
			if(rightChild.data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = rightChild;
			} else {
				parent.right = rightChild;
			}
		}
		else root = rightChild;
		
		//assigning new heights
		node.height = Math.max(node.left != null ? node.left.height : -1, node.right != null ? node.right.height : -1) + 1;
		rightChild.height = Math.max(rightChild.left != null ? rightChild.left.height : -1, rightChild.right != null ? rightChild.right.height : -1) + 1;
		if(parent != null) parent.height = Math.max(parent.left != null ? parent.left.height : -1, parent.right != null ? parent.right.height : -1) + 1;
		
		return parent;
	}
	
	private AVLTreeNode leftRightCase(AVLTreeNode node) {
		AVLTreeNode leftChild = node.left;
		
		//Left rotate on 'leftChild'
		AVLTreeNode left_rightChild = leftChild.right;
		AVLTreeNode parent = node.parent;
		
		leftChild.right = left_rightChild.left;
		if(left_rightChild.left != null) left_rightChild.left.parent = leftChild.right;
		
		node.left = left_rightChild;
		left_rightChild.parent = node;
		
		left_rightChild.left = leftChild;
		leftChild.parent = left_rightChild;
		
		leftChild.height = Math.max(leftChild.right != null ? leftChild.right.height : -1, leftChild.left != null ? leftChild.left.height : -1) + 1;
		left_rightChild.height = Math.max(left_rightChild.right != null ? left_rightChild.right.height : -1, left_rightChild.left != null ? left_rightChild.left.height : -1) + 1;
		node.height = Math.max(node.right != null ? node.right.height : -1, node.left != null ? node.left.height : -1) + 1;
		
		
		//Right rotate on 'node'
		leftChild = left_rightChild;
		
		node.left = leftChild.right;
		if(leftChild.right != null) leftChild.right.parent = node;
							
		leftChild.right = node;
		node.parent = leftChild;
		
		if(parent != null) {
			leftChild.parent = parent;
			
			if(leftChild.data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = leftChild;
			} else {
				parent.right = leftChild;
			}
		}
		else root = leftChild;
		
		//assigning new heights
		node.height = Math.max(node.right != null ? node.right.height : -1, node.left != null ? node.left.height : -1) + 1;
		leftChild.height = Math.max(leftChild.right != null ? leftChild.right.height : -1, leftChild.left != null ? leftChild.left.height : -1) + 1;
		if(parent != null) parent.height = Math.max(parent.right != null ? parent.right.height : -1, parent.left != null ? parent.left.height : -1) + 1;
		
		return parent;
	}
	
	private AVLTreeNode rightRightCase(AVLTreeNode node){
		AVLTreeNode rightChild = node.right;
		
		AVLTreeNode parent = node.parent;
		
		node.right = rightChild.left;
		if(rightChild.left != null) rightChild.left.parent = node;
							
		rightChild.left = node;
		node.parent = rightChild;
		
		if(parent != null) {
			rightChild.parent = parent;
			
			if(rightChild.data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = rightChild;
			} else {
				parent.right = rightChild;
			}
		}
		else root = rightChild;
		
		//assigning new heights
		node.height = Math.max(node.left != null ? node.left.height : -1, node.right != null ? node.right.height : -1) + 1;
		rightChild.height = Math.max(rightChild.left != null ? rightChild.left.height : -1, rightChild.right != null ? rightChild.right.height : -1) + 1;
		if(parent != null) parent.height = Math.max(parent.left != null ? parent.left.height : -1, parent.right != null ? parent.right.height : -1) + 1;
		
		return parent;
	}
	
	private AVLTreeNode leftLeftCase(AVLTreeNode node){
		AVLTreeNode leftChild = node.left;		
		AVLTreeNode parent = node.parent;
		
		node.left = leftChild.right;
		if(leftChild.right != null) leftChild.right.parent = node;
							
		leftChild.right = node;
		node.parent = leftChild;
		
		if(parent != null) {
			leftChild.parent = parent;
			if(leftChild.data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = leftChild;
			} else {
				parent.right = leftChild;
			}
		} else root = leftChild;
		
		//assigning new heights
		node.height = Math.max(node.right != null ? node.right.height : -1, node.left != null ? node.left.height : -1) + 1;
		leftChild.height = Math.max(leftChild.right != null ? leftChild.right.height : -1, leftChild.left != null ? leftChild.left.height : -1) + 1;
		if(parent != null) parent.height = Math.max(parent.right != null ? parent.right.height : -1, parent.left != null ? parent.left.height : -1) + 1;
		
		return parent;
	}
	
	private AVLTreeNode getInorderSuccessor(AVLTreeNode currentRoot){
		AVLTreeNode node = currentRoot;
		
		while(node != null && node.left != null){
			node = node.left;
		}
		
		return node;
	}
	
	public void preOrderTraversal(AVLTreeNode node){
		if(node != null) {
			preorder(node, 0);
		}
	}
	
	private void preorder(AVLTreeNode node, int level) {
		if(node != null){
			System.out.println("{"+node + ", Level "+level+", Height "+node.height+"}");
			preorder(node.left, level + 1);
			preorder(node.right, level + 1);
		}
	}
	
	public void postOrderTraversal(AVLTreeNode node){
		if(node != null) {
			postorder(node, 0);
		}
	}
	
	private void postorder(AVLTreeNode node, int level) {
		if(node != null){
			postorder(node.left, level + 1);
			postorder(node.right, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
		}
	}
	
	public void inOrderTraversal(AVLTreeNode node){
		if(node != null) {
			inorder(node, 0);
		}
	}
	
	private void inorder(AVLTreeNode node, int level) {
		if(node != null){
			inorder(node.left, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
			inorder(node.right, level + 1);
		}
	}
}
