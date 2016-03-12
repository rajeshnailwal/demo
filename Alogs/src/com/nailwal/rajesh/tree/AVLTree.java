package com.nailwal.rajesh.tree;

import com.nailwal.rajesh.data.Data;
import com.nailwal.rajesh.data.TreeNode;

/**
 * AVL tree is a BST which is balanced
 * @author Rajesh
 *
 */
public class AVLTree {

	public static void main(String[] args) {
		/*Test case 1 passed
		TreeNode<Integer> root = insert(null, 2);
		root = insert(root, 3);
		root = insert(root, 4);*/
		
		/* Test case 2 passed
		TreeNode<Integer> root = insert(null, 3);
		insert(root, 2);
		insert(root, 4);
		insert(root, 5);
		insert(root, 6);*/
		
		/* Test case 3 failed*/
		TreeNode<Integer> root = insert(null, 14);
		root = insert(root, 25);
		root = insert(root, 21);
		root = insert(root, 10);
		root = insert(root, 23);
		root = insert(root, 7);
		root = insert(root, 26);
		root = insert(root, 12);
		root = insert(root, 30);
		root = insert(root, 16);
		root = insert(root, 19);
		
		/* Test case 4 failed
		TreeNode<Integer> root = insert(null, 30);
		root = insert(root, 29);
		root = insert(root, 27);
		root = insert(root, 13);*/
		
		/* Test case 5 failed
		TreeNode<Integer> root = insert(null, 190);
		root = insert(root, 108);
		root = insert(root, 143);
		root = insert(root, 144);
		root = insert(root, 111);
		root = insert(root, 51);
		root = insert(root, 17);
		root = insert(root, 90);
		root = insert(root, 184);
		root = insert(root, 172);
		root = insert(root, 196);
		root = insert(root, 83);
		root = insert(root, 20);
		root = insert(root, 117);
		root = insert(root, 7);
		root = insert(root, 188);
		root = insert(root, 114);
		root = insert(root, 173);
		root = insert(root, 62);
		root = insert(root, 112);
		root = insert(root, 70);
		root = insert(root, 12);
		root = insert(root, 55);
		root = insert(root, 99);*/
		
		/* Test case 6 failed
		TreeNode<Integer> root = insert(null, 17);
		root = insert(root, 119);
		root = insert(root, 101);
		root = insert(root, 97);
		root = insert(root, 71);
		root = insert(root, 76);
		root = insert(root, 6);
		root = insert(root, 142);
		root = insert(root, 81);
		root = insert(root, 34);
		root = insert(root, 173);
		root = insert(root, 122);
		root = insert(root, 169);
		root = insert(root, 129);
		root = insert(root, 181);
		root = insert(root, 39);
		root = insert(root, 16);
		root = insert(root, 35);
		root = insert(root, 24);
		root = insert(root, 74);
		root = insert(root, 70);
		root = insert(root, 120);
		root = insert(root, 176);
		root = insert(root, 75);
		root = insert(root, 2);
		root = insert(root, 186);
		root = insert(root, 104);
		root = insert(root, 21);
		root = insert(root, 14);
		root = insert(root, 124);
		root = insert(root, 47);
		root = insert(root, 95);
		root = insert(root, 105);
		root = insert(root, 99);
		root = insert(root, 94);
		root = insert(root, 87);
		root = insert(root, 165);
		root = insert(root, 139);
		root = insert(root, 36);
		root = insert(root, 69);*/
		
		/* Test case 7 failed 
		TreeNode<Integer> root = insert(null, 138);
		root = insert(root, 180);
		root = insert(root, 113);
		root = insert(root, 136);
		root = insert(root, 169);
		root = insert(root, 118);
		root = insert(root, 28);
		root = insert(root, 191);
		root = insert(root, 150);
		root = insert(root, 195);
		root = insert(root, 152);
		root = insert(root, 31);
		root = insert(root, 123);
		root = insert(root, 16);
		root = insert(root, 185);
		root = insert(root, 17);
		root = insert(root, 45);
		root = insert(root, 196);
		root = insert(root, 11);
		root = insert(root, 49);
		root = insert(root, 94);
		root = insert(root, 157);
		root = insert(root, 129);
		root = insert(root, 173);
		root = insert(root, 154);
		root = insert(root, 32);
		root = insert(root, 12);
		root = insert(root, 2);
		root = insert(root, 117);
		root = insert(root, 149);
		root = insert(root, 194);
		root = insert(root, 186);
		root = insert(root, 59);
		root = insert(root, 99);
		root = insert(root, 142);
		root = insert(root, 90);
		root = insert(root, 170);
		root = insert(root, 183);
		root = insert(root, 57);
		root = insert(root, 141);
		root = insert(root, 127);
		root = insert(root, 58);
		root = insert(root, 122);
		root = insert(root, 189);
		root = insert(root, 66);
		root = insert(root, 177);
		root = insert(root, 104);
		root = insert(root, 188);*/
		inOrder(root);
	}
	
	private static TreeNode<Integer> insert(TreeNode<Integer> root, int val) {
	    // Logic for insertion
	    TreeNode<Integer> newNode = new TreeNode<Integer>();
	    newNode.data = new Data<Integer>(val);
	    root = BinarySearchTree.insert(root, newNode);
	    
	    /*
	     * This logic will be used when parent reference doesn't exist in every node
	     */
	    assignHeight(root);
	    root = calculateWeightAndBalance(root, null, newNode);
	    /*
	     * This logic will be used when parent reference exists in every node
	     * newNode.height = 1;
	     * root = performBalancing(newNode);
	     * 
	     */	    
	    return root;            
	}
	
	private static TreeNode<Integer> delete(TreeNode<Integer> root, Data<Integer> data){
		root = BinarySearchTree.delete(root, data);
		assignHeight(root);
		performBalancing(root);
		return root;
	}
	
	/**
	 * This logic will be used when parent reference exists in every node
	 * @param newNode
	 * @return
	 */
	private static TreeNode<Integer> performBalancing(TreeNode<Integer> nd){
		TreeNode<Integer> node = nd;
		TreeNode<Integer> parent = null;
		while(node != null){
			int leftHeight = node.left != null ? node.left.height : 0;
			int rightHeight = node.right != null ? node.right.height : 0;

			int balanceFactor = leftHeight - rightHeight;

			if(balanceFactor < -1 || balanceFactor > 1){
				if(node.data.getData() < nd.data.getData() 
						&& node.right.data.getData() < nd.data.getData()){//right-right
					rotateLeftAndResetHeights(node);
				} else if(node.data.getData() < nd.data.getData() 
						&& node.right.data.getData() >= nd.data.getData()){//right-left
					rotateRightAndResetHeights(node.right);
					rotateLeftAndResetHeights(node);
				} else if(node.data.getData() >= nd.data.getData() 
						&& node.left.data.getData() >= nd.data.getData()){//left-left
					rotateRightAndResetHeights(node);
				} else if(node.data.getData() >= nd.data.getData() 
						&& node.left.data.getData() < nd.data.getData()){//left-right
					rotateLeftAndResetHeights(node.left);
					rotateRightAndResetHeights(node);
				}
			} else {
				node.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
			}
			parent = node;
			node = node.parent;
		}
		if(parent == null){
			return nd;
		} else {
			return parent;
		}
	}
	
	/**
	 * This logic will be used when parent reference exists in every node
	 * @param rotateOn
	 */
	private static void rotateLeftAndResetHeights(TreeNode<Integer> rotateOn){
		TreeNode<Integer> parent = rotateOn.parent;
		TreeNode<Integer> child = rotateOn.right;
		if(parent != null){
			if(parent.left == rotateOn){
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		
		child.parent = parent;
		rotateOn.right = child.left;
		if(child.left != null){
			child.left.parent = rotateOn;						
		}
		rotateOn.parent = child;
		child.left = rotateOn;
		
		//adjust heights
		int leftHeight = rotateOn.left != null ? rotateOn.left.height : 0;
		int rightHeight = rotateOn.right != null ? rotateOn.right.height : 0;		
		rotateOn.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;	
		
		leftHeight = child.left != null ? child.left.height : 0;
		rightHeight = child.right != null ? child.right.height : 0;		
		child.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		
		if(parent != null){
			leftHeight = parent.left != null ? parent.left.height : 0;
			rightHeight = parent.right != null ? parent.right.height : 0;
			parent.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		}
	}
	
	/**
	 * This logic will be used when parent reference exists in every node
	 * @param rotateOn
	 */
	private static void rotateRightAndResetHeights(TreeNode<Integer> rotateOn){
		TreeNode<Integer> parent = rotateOn.parent;
		TreeNode<Integer> child = rotateOn.left;
		if(parent != null){
			if(parent.left == rotateOn){
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		child.parent = parent;
		rotateOn.left = child.right;
		if(child.right != null){
			child.right.parent = rotateOn;						
		}
		rotateOn.parent = child;
		child.right = rotateOn;
		
		//adjust heights
		int leftHeight = rotateOn.left != null ? rotateOn.left.height : 0;
		int rightHeight = rotateOn.right != null ? rotateOn.right.height : 0;		
		rotateOn.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;	
		
		leftHeight = child.left != null ? child.left.height : 0;
		rightHeight = child.right != null ? child.right.height : 0;		
		child.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		
		if(parent != null){
			leftHeight = parent.left != null ? parent.left.height : 0;
			rightHeight = parent.right != null ? parent.right.height : 0;
			parent.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		}
	}
	
	private static TreeNode<Integer> calculateWeightAndBalance(TreeNode<Integer> node, TreeNode<Integer> parent, TreeNode<Integer> newNode){
		TreeNode<Integer> subTreeRoot = node;
	    if(node != null){
	    	calculateWeightAndBalance(node.left, node, newNode);
	    	calculateWeightAndBalance(node.right, node, newNode);
	    	
	    	int leftHeight = 0, rightHeight = 0;
	    	if(node.left != null){
	    		leftHeight = node.left.height;
	    	}
	    	
	    	if(node.right != null){
	    		rightHeight = node.right.height;
	    	}
	    	
	    	int balanceFactor = leftHeight - rightHeight;
	    	
	    	if(balanceFactor < -1 || balanceFactor > 1){	    		
	    		if(node.data.getData() < newNode.data.getData() 
						&& node.right.data.getData() < newNode.data.getData()){//right-right
	    			subTreeRoot = rotateLeftAndResetHeights(node, parent, node.right); 
				} else if(node.data.getData() < newNode.data.getData() 
						&& node.right.data.getData() >= newNode.data.getData()){//right-left
					rotateRightAndResetHeights(node.right, node, node.right.left);
					subTreeRoot = rotateLeftAndResetHeights(node, parent, node.right);					
				} else if(node.data.getData() >= newNode.data.getData() 
						&& node.left.data.getData() >= newNode.data.getData()){//left-left
					subTreeRoot = rotateRightAndResetHeights(node, parent, node.left);					
				} else if(node.data.getData() >= newNode.data.getData() 
						&& node.left.data.getData() < newNode.data.getData()){//left-right
					rotateLeftAndResetHeights(node.left, node, node.left.right);
					subTreeRoot = rotateRightAndResetHeights(node, parent, node.left);
				}
	    	}
	    	
	    }
	    
	    return subTreeRoot;	    
	}
	
	/**
	 * 
	 * @param node
	 * @param parent
	 * @param child
	 * @return new root of this subtree
	 */
	private static TreeNode<Integer> rotateLeftAndResetHeights(TreeNode<Integer> node, TreeNode<Integer> parent, TreeNode<Integer> child){
		if(parent != null){
			if(parent.left == node){
				parent.left = child;
			} else {
				parent.right = child;
			}
		}		
		node.right = child.left;
		child.left = node;
		
		//adjust heights
		int leftHeight = node.left != null ? node.left.height : 0;
		int rightHeight = node.right != null ? node.right.height : 0;		
		node.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;	
		
		leftHeight = child.left != null ? child.left.height : 0;
		rightHeight = child.right != null ? child.right.height : 0;		
		child.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		
		if(parent != null){
			leftHeight = parent.left != null ? parent.left.height : 0;
			rightHeight = parent.right != null ? parent.right.height : 0;
			parent.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		}
		
		return child;
	}
	/**
	 * 
	 * @param node
	 * @param parent
	 * @param child
	 * @return new root of this subtree
	 */
	private static TreeNode<Integer> rotateRightAndResetHeights(TreeNode<Integer> node, TreeNode<Integer> parent, TreeNode<Integer> child){
		if(parent != null){
			if(parent.left == node){
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		node.left = child.right;
		child.right = node;
		
		//adjust heights
		int leftHeight = node.left != null ? node.left.height : 0;
		int rightHeight = node.right != null ? node.right.height : 0;		
		node.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;	
		
		leftHeight = child.left != null ? child.left.height : 0;
		rightHeight = child.right != null ? child.right.height : 0;		
		child.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		
		if(parent != null){
			leftHeight = parent.left != null ? parent.left.height : 0;
			rightHeight = parent.right != null ? parent.right.height : 0;
			parent.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
		}
		
		return child;
	}
	
	private static int assignHeight(TreeNode<Integer> node){
	    int height = -1;    
	    if(node != null){
	        int leftHeight = assignHeight(node.left);
	        int rightHeight = assignHeight(node.right);        
	        height = leftHeight > rightHeight ? leftHeight : rightHeight;        
	        node.height = height + 1;
	    }
	    
	    return height + 1;
	}
	
	private static void inOrder(TreeNode<Integer> node){
		if(node != null){
			inOrder(node.left);
			int leftHeight = node.left == null ? 0 : node.left.height;
			int rightHeight = node.right == null ? 0 : node.right.height;
			System.out.print(node.data +"(BF="+(leftHeight - rightHeight)+")");
			inOrder(node.right);
		}
	}
}
