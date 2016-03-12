package com.nailwal.rajesh.tree;

import com.nailwal.rajesh.data.Data;
import com.nailwal.rajesh.data.TreeNode;

public class BinarySearchTree {

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
		
		/* Test case 3 failed
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
		*/
		/* Test case 4 failed
		TreeNode<Integer> root = insert(null, 30);
		root = insert(root, 29);
		root = insert(root, 27);
		root = insert(root, 13);
		*/
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
		root = insert(root, 99);
		*/
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
		root = insert(root, 69);
		*/
		
		/* Test case 7 failed */
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
		root = insert(root, 188);
	}	
	
	public static TreeNode<Integer> insert(TreeNode<Integer> root, int val){
		TreeNode<Integer> newNode = new TreeNode<Integer>();
		newNode.data = new Data<Integer>(val);
		return insert(root, newNode);
	}

	public static TreeNode<Integer> insert(TreeNode<Integer> root, TreeNode<Integer> newNode){
		TreeNode<Integer> node = root;
        TreeNode<Integer> parent = null;
        while(node != null){
            parent = node;
            if(node.data.getData() < newNode.data.getData()){
                node = node.right;
            } else {
                node = node.left;
            }
        }
        
        if(root == null){
	        root = newNode;
	    } else {
	    	if(parent.data.getData() < newNode.data.getData()){
	        	parent.right = newNode;
	        } else {
	        	parent.left = newNode;
	        }
	        newNode.parent = parent;
	    }
	    return root;
	}
	
	public static TreeNode<Integer> delete(TreeNode<Integer> root, Data<Integer> data){
		TreeNode<Integer> node = root;
		TreeNode<Integer> parent = null;
		while(node != null){
			if(node.data.getData() == data.getData()){
				break;
			} else if(node.data.getData() < data.getData()){
				parent = node;
				node = node.right;
			} else {
				parent = node;
				node = node.left;
			}
		}
		
		if(node != null){
			if(node.left == null && node.right == null){
				if(parent == null){//it means root is the only node in tree and this node is being deleted
					root = parent;
				} else if(parent.left == node){//simply delete the node 
					parent.left = null;
				} else {
					parent.right = null;
				}
			} else if(node.left == null && node.right != null){
				if(parent == null){//it means root is the node which is being deleted
					root = node.right;
				} else if(parent.left == node){
					parent.left = node.right;
				} else {
					parent.right = node.right;
				}
				node.right = null;
			} else if(node.left != null && node.right == null){
				if(parent == null){//it means root is the node which is being deleted
					root = node.left;
				} else if(parent.left == node){
					parent.left = node.left;
				} else {
					parent.right = node.left;
				}
				node.left = null;
			} else {//node.left != null && node.right != null
				//get successor of this node in inorder traversal
				TreeNode<Integer> successor = node.right;
				TreeNode<Integer> successorParent = node;
				
				while(successor.left != null){
					successorParent = successor;
					successor = successor.left;
				}
				
				//successor will be either root of right sub-tree
				//or the left most child of right sub-tree
				//hence there will be no left child of successor
				//so if successor's right child exist make it 
				//the child of successor's parent
				if(successor.right != null){
					if(successorParent.left == successor){
						successorParent.left = successor.right;
					} else {
						successorParent.right = successor.right;
					}
				} else {
					//as successor will be either a single node
					//in right sub-tree of node or left most leaf
					//node in right sub-tree of node
					successorParent.left = null;
				}
				
				successor.right = null;
				
				//replace node with the successor
				if(parent != null){
					if(parent.left == node){
						parent.left = successor;
					} else {
						parent.right = successor;
					}
				} else {
					root = successor;
				}
				successor.left = node.left;
				successor.right = node.right;
				node.left = null;
				node.right = null;
			}
		}		
		return root;
	}
	
	public static TreeNode<Integer> search(TreeNode<Integer> root, Data<Integer> data){
		TreeNode<Integer> node = root;
		while(node != null){
			if(node.data.getData() == data.getData()){
				return node;
			} else if(node.data.getData() < data.getData()){
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}
}
