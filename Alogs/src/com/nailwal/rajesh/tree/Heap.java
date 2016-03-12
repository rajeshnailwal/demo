package com.nailwal.rajesh.tree;

import java.util.ArrayList;

import com.nailwal.rajesh.data.Data;
import com.nailwal.rajesh.data.TreeNode;

public class Heap {

	//private ArrayList<TreeNode<Integer>> dynamicArray = new ArrayList<TreeNode<Integer>>();
	
	/*
	 * Create max heap(maxify)
	 */
	public static TreeNode<Integer> insert(TreeNode<Integer> root, Data<Integer> data){
		TreeNode<Integer> newNode = new TreeNode<Integer>();
		newNode.data = data;
		return insert(root, newNode);
	}
	
	public static ArrayList<TreeNode<Integer>> insert(ArrayList<TreeNode<Integer>> heap, Data<Integer> data){
		TreeNode<Integer> newNode = new TreeNode<Integer>();
		newNode.data = data;
		return insert(heap, newNode);
	}
	
	// insert in max heap
	public static TreeNode<Integer> insert(TreeNode<Integer> root, TreeNode<Integer> newNode) {
		ArrayList<TreeNode<Integer>> dynamicArray = new ArrayList<TreeNode<Integer>>();
		if(root != null){
			//using dynamic array as a queue and count to simulate poll
			//though i can use queue(Queue<TreeNode<Integer>>) and 
			//perform offer and poll operation but I am avoiding it so 
			//that we can make use of existing array. It will ease the
			//re-positioning of new node by comparing with parent.
			int index = 0;
			int pos = index;
			dynamicArray.add(root);
			
			TreeNode<Integer> temp = null;
			
			while((temp = dynamicArray.get(index)) != null){
				if(temp.left != null){
					dynamicArray.add(++pos, temp.left);
				} else {
					temp.left = newNode;
					dynamicArray.add(++pos, newNode);
					break;
				}
				
				if(temp.right != null){
					dynamicArray.add(++pos, temp.right);
				} else {
					temp.right = newNode;
					dynamicArray.add(++pos, newNode);
					break;
				}
				++index;
			}
			// move node to its correct position in heap
			repositionUp(dynamicArray, dynamicArray.size() - 1);
		} else {
			dynamicArray.add(0, root);
		}
		return dynamicArray.get(0);
	}
	
	public static ArrayList<TreeNode<Integer>> insert(ArrayList<TreeNode<Integer>> heap, TreeNode<Integer> newNode){
		if(heap != null){
			heap.add(heap.size(), newNode);
			int insertionPos = heap.size() - 1;
			
			//manage heap as tree
			if(heap.size() > 1){
				if(insertionPos % 2 == 1){ //It means newNode is a left child of its parent
					heap.get((insertionPos - 1)/ 2).left = newNode;
				} else {
					heap.get((insertionPos - 1)/ 2).right = newNode;
				}
			}
			
			// move node to its correct position in heap
			repositionUp(heap, heap.size()-1);
		} else {
			heap = new ArrayList<TreeNode<Integer>>();
			heap.add(0, newNode);
		}
		return heap;
	}
	
	private static void repositionUp(ArrayList<TreeNode<Integer>> array, int nodePos){
		int parentPos = (nodePos-1)/2;
		int grandParentPos = (parentPos-1)/2;
		while(parentPos != nodePos && swapParentChild(array, grandParentPos, parentPos, nodePos)){//at root both parentPos and nodePos will be same i.e. 0				
			nodePos = parentPos;
			parentPos = grandParentPos;
			grandParentPos = (grandParentPos - 1)/2;
		}
	}
	
	private static void repositionDown(ArrayList<TreeNode<Integer>> array, int nodePos){
		int parentPos = (nodePos - 1)/2;
		int childPos = -1;
		TreeNode<Integer> node = array.get(nodePos);
		while(true){
			if(node.left != null && node.right != null){
				if(node.left.data.getData() < node.right.data.getData() 
						&& node.data.getData() < node.right.data.getData()){
					childPos = nodePos * 2 + 2;
					swapParentChild(array, parentPos, nodePos, childPos);
				} else if(node.left.data.getData() > node.right.data.getData()
						&& node.data.getData() < node.left.data.getData()){
					childPos = nodePos * 2 + 1;
					swapParentChild(array, parentPos, nodePos, childPos);
				}
			} else if(node.left != null && node.data.getData() < node.left.data.getData()){
				childPos = nodePos * 2 + 1;
				swapParentChild(array, parentPos, nodePos, childPos);
			} else if(node.right != null && node.data.getData() < node.right.data.getData()){
				childPos = nodePos * 2 + 2;
				swapParentChild(array, parentPos, nodePos, childPos);
			} else {
				break;
			}
			parentPos = nodePos;
			nodePos = childPos;
		}
	}
	
	private static boolean swapParentChild(ArrayList<TreeNode<Integer>> array, int grandParentPos, int parentPos, int nodePos){
		TreeNode<Integer> grandParent = array.get(grandParentPos);
		TreeNode<Integer> parent = array.get(parentPos);
		TreeNode<Integer> node = array.get(nodePos);
		boolean isSuccessful = false;
		if(node.data.getData() > parent.data.getData()){
			
			//Relations which will be updated
			//		  grandparent										grandparent
			//					\												  \
			//					 \												   \
			//					parent											  node
			//				   / 	 \											 /	  \
			//				  /		  \			  ---------->					/	   \
			//			Right child   node								 Right child   parent
			//			 of parent	  /	 \								  of parent	   /	\
			//						 /    \											  / 	 \
			//					    /      \										 /		  \
			//				 Left child   Right child							Left child   Right child
			//				   of node      of node								  of node      of node
			
			
			//Make node as a child of parent's parent
			if(grandParentPos != parentPos){//at root both grandParentPos and parentPos will be same i.e. 0
				//		  grandparent										grandparent
				//					\												  \
				//					 \					-------->					   \
				//					parent				          					  node
				
				if(grandParent.left == parent){
					grandParent.left = node;
				} else {
					grandParent.right = node;
				}
			}
			
			//save node's children so that later on they can be assigned as parent's children
			TreeNode<Integer> tempLeft = node.left;
			TreeNode<Integer> tempRight = node.right;
			
			//if node is left child of parent then make parent as a left child of node
			//and make right child of parent as right child of node 
			//else make parent a right child of node and make left child of parent as
			//left child of node
			
			//					parent											  node
			//				   / 	 \											 /	  \
			//				  /		  \			  ---------->					/	   \
			//			Right child   node								 Right child   parent
			//			 of parent	  									  of parent	   

			if(parent.left == node){
				node.left = parent;	
				node.right = parent.right;
			} else {
				node.right = parent;
				node.left = parent.left;
			}
			
			//now assign saved children of node as children of parent
			
			//						node								 parent
			//			           /    \							    /	  \
			//			    	  /      \		---------->			   / 	   \
			//					 /        \							  /		    \
			//				 Left child   Right child			Left child   Right child
			//				   of node      of node				  of node      of node
		
			parent.left = tempLeft;
			parent.right = tempRight;
			
			//maintain array heap(swap parent and node positions)
			array.set(parentPos, node);
			array.set(nodePos, parent);
			isSuccessful = true;
		}
		return isSuccessful;
	}
	
	/**
	 * Implementation of heapsort algo (Complexity NlogN)
	 * @param array
	 */
	public static void sort(ArrayList<TreeNode<Integer>> array){
		if(array != null && array.size() > 0){
			int counter = array.size(); 
			
			while(counter > 1){ //run till virtual array size(counter) reduced to 2
				//In max heap(array) first node(i.e. root) will be largest
				//then swap it with the last node of modified array and reduce 
				//array size(counter) by 1
				//Note :- array size will be continuously decreasing. But actually
				//array size is same we are just simulating reduction of size by 
				//using counter variable so in a reduced array(with total reduction
				//k) of size n - k the last node will be n-k-1 and its index will be 
				//n-k-2
				TreeNode<Integer> olderRoot = array.get(0);
				array.set(0, array.get(--counter));
				array.set(counter, olderRoot);
				
				//To maintain the tree heap from the reduced array
				//(i.e. array minus last node of modified array/tree)
				TreeNode<Integer> parent = array.get((counter - 1) / 2);
				TreeNode<Integer> newRoot = array.get(0);
				
				//make the parent of older last node which is now root null
				if(parent.left == newRoot){
					parent.left = null;
				} else {
					parent.right = null;
				}
				
				//make children of older root(highest node) the children of new root
				//if new root is left or right child of old root it means tree is now
				//of height 2 and 
				if(newRoot != olderRoot.left){
					newRoot.left = olderRoot.left;
				} else {
					newRoot.left = null;
				}
				
				if(newRoot != olderRoot.right){
					newRoot.right = olderRoot.right;
				} else {
					newRoot.right = null;
				}				
				
				//break links of highest node which is now at the end of original array
				olderRoot.left = null;
				olderRoot.right = null;
				
				//new root is comparatively smaller than its children so need to reposition
				//the new root
				repositionDown(array, 0);
			}	
			
			//to convert it into max heapify reverse array
			counter = array.size() / 2;
			for(int i = 0; i < counter; ++i){
				TreeNode<Integer> temp = array.get(i);
				int loc = array.size() - 1 - i;
				array.set(i, array.get(loc));
				array.set(loc, temp);
			}
			
			//make parent child relationship
			for(int i = 0; i < array.size(); ++i){
				int childIndex = i * 2 + 1;
				if(childIndex < array.size()){
					array.get(i).left = array.get(childIndex);
				} else {
					break;
				}
				
				childIndex = i * 2 + 2;
				if(childIndex < array.size()){
					array.get(i).right = array.get(childIndex);
				} else {
					break;
				}
			}
		}
	}
	
	public static void delete(ArrayList<TreeNode<Integer>> array, int nodePos){
		
		if(nodePos > -1 && nodePos < array.size()){
			
			//Replace the node to be deleted with last node of the tree
			//and reposition the replaced last node to its proper position
			//to maintain heap
			int lastNodeIndex = array.size() - 1;
			int lastNodeParentIndex = (lastNodeIndex - 1) / 2;
			
			TreeNode<Integer> lastNodeParent = array.get(lastNodeParentIndex);
			TreeNode<Integer> lastNode = array.get(lastNodeIndex);
			
			//on moving last node to some other place parent of last node
			//will now refer to right/left child(whichever was the position
			//for moved node) as null for last node position
			if(lastNode != lastNodeParent){//i.e. last node is not root
				if(lastNodeParent.left == lastNode){
					lastNodeParent.left = null;
				} else {
					lastNodeParent.right = null;
				}
			}
			
			TreeNode<Integer> node = array.get(nodePos);
			TreeNode<Integer> parent = array.get((nodePos - 1)/ 2);
			
			//make the children of node, now the children of last node
			if(node.left != lastNode){
				lastNode.left = node.left;
			}
			if(node.right != lastNode){
				lastNode.right = node.right;
			}
			
			//make the parent of node, now the parent of last node
			if(node != parent && node != lastNode){//i.e. node is not the root and node is not last node
				if(parent.left == node){
					parent.left = lastNode;
				} else {
					parent.right = lastNode;
				}
			}
			
			//maintain heap in array
			lastNode = array.remove(lastNodeIndex);
			if(nodePos != lastNodeIndex){
				array.set(nodePos, lastNode);
				repositionDown(array, nodePos);
			}
		}
	}
		
	public static void main(String[] args) {
		ArrayList<TreeNode<Integer>> heap = new ArrayList<TreeNode<Integer>>();
		Heap.insert(heap, new Data<Integer>(500));
		Heap.insert(heap, new Data<Integer>(600));
		Heap.insert(heap, new Data<Integer>(800));
		Heap.insert(heap, new Data<Integer>(100));
		Heap.insert(heap, new Data<Integer>(30));
		Heap.insert(heap, new Data<Integer>(300));
		Heap.insert(heap, new Data<Integer>(450));
		Heap.insert(heap, new Data<Integer>(10));
		Heap.insert(heap, new Data<Integer>(60));
		Heap.sort(heap);
		TreeTraversal.preOrder(heap.get(0));
		System.out.println();
		Heap.delete(heap, 2);
		TreeTraversal.preOrder(heap.get(0));
		Heap.sort(heap);
		System.out.println();
		TreeTraversal.preOrder(heap.get(0));
	}
}
