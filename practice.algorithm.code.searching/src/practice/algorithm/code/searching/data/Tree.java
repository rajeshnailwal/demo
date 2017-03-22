package practice.algorithm.code.searching.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Tree<E extends Comparable<E>> {
	
	public TreeNode<E> getCompleteBinaryTree(Data<E>[] arr) {
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>(arr.length);
		TreeNode<E> node = null;
		
		Arrays.stream(arr).forEach(data -> {
			list.add(new TreeNode<E>(data));
		});
		
		int left = -1, right = -1;
		for(int i = 0; i < list.size() / 2; ++i){
			node = list.get(i);
			left = i*2 + 1;
			right = i*2 + 2;
			node.left = list.get(left);
			if(right < list.size()){
				node.right = list.get(i * 2 + 2);
			}
		}
		
		return list.get(0);
	}
	
	public TreeNode<E> getBinraySearchTree(List<Data<E>> dataList) {
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>(dataList.size());
		TreeNode<E> root = null;
		
		dataList.stream().forEach(data -> {
			list.add(new TreeNode<E>(data));
		});
		
		for(TreeNode<E> node : list){
			root = addNodeInBST(root, node);
		}
		
		return root;
	}
	
	public TreeNode<E> addNodeInBST(TreeNode<E> root, TreeNode<E> node){
		if(root == null){
			root = node;
			root.height = 0;
		} else {
			TreeNode<E> nd = root;
			TreeNode<E> parent = null;
			
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
			
			while(nd != null){
				stack.push(nd);
				if(node.data.getData().compareTo(nd.data.getData()) < 0){//node is smaller than nd
					parent = nd;
					nd = nd.left;
				} else if(node.data.getData().compareTo(nd.data.getData()) > 0){//node is greater than nd
					parent = nd;
					nd = nd.right;
				} else {
					break;
				}
			}
			
			//if nd is not null it means 'node' to be inserted is already there in BST 
			//else a position has been found where 'node' can be inserted 
			if(nd == null){
				if(node.data.getData().compareTo(parent.data.getData()) < 0){
					parent.left = node;
					node.height = 0;
				} else if(node.data.getData().compareTo(parent.data.getData()) > 0){
					parent.right = node;
					node.height = 0;
				}
				
				//balancerNode is a node where we check, whether the tree at this node (as root) is a balanced BST
				TreeNode<E> balancerNode = stack.pop();
				
				TreeNode<E> temp = null;
				
				//balancing BST
				while(balancerNode != null){
					int leftHeight = balancerNode.left != null ? balancerNode.left.height : -1;
					int rightHeight = balancerNode.right != null ? balancerNode.right.height : -1; 
					
					if(leftHeight - rightHeight >= 2){//left side is heavier
						if(node.data.getData().compareTo(balancerNode.data.getData()) < 0){
							
							//it means node has been added in left-left subtree of balancerNode
							if(node.data.getData().compareTo(balancerNode.left.data.getData()) <= 0){
								//right rotate on position of balancer node 
								temp = balancerNode.left;
								balancerNode.left = temp.right;
								temp.right = balancerNode;
								
								//reassign height of rotated nodes
								leftHeight = temp.right.left != null ? temp.right.left.height : -1;
								rightHeight = temp.right.right != null ? temp.right.right.height : -1;
								temp.right.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
								
								leftHeight = temp.left.left != null ? temp.left.left.height : -1;
								rightHeight = temp.left.right != null ? temp.left.right.height : -1;
								temp.left.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
								
								temp.height = temp.right.height > temp.left.height ? temp.right.height + 1 : temp.left.height + 1;
								
								if(stack.size() > 0) {
									parent = stack.pop();
								} else {
									parent = null;
								}
								
								if(parent != null){
									if(parent.left == balancerNode) parent.left = temp;
									else if (parent.right == balancerNode) parent.right = temp;	
									
									balancerNode = parent;
								} else {
									root = temp;
									break;
								}
								 
							}
							//it means node has been added in left-right(which was null) subtree of balancerNode
							else if(node.data.getData().compareTo(balancerNode.left.data.getData()) >= 0){
								//left rotate on position of balancer node's left child
								temp = balancerNode.left.right;
								balancerNode.left.right = temp.left;
								temp.left = balancerNode.left;
								balancerNode.left = temp;
								
								//right rotate on position of balancer node 
								temp = balancerNode.left;
								balancerNode.left = temp.right;
								temp.right = balancerNode;
								
								//reassign height of rotated nodes
								leftHeight = temp.right.left != null ? temp.right.left.height : -1;
								rightHeight = temp.right.right != null ? temp.right.right.height : -1;
								temp.right.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
								
								leftHeight = temp.left.left != null ? temp.left.left.height : -1;
								rightHeight = temp.left.right != null ? temp.left.right.height : -1;
								temp.left.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
								
								temp.height = temp.right.height > temp.left.height ? temp.right.height + 1 : temp.left.height + 1;
								
								if(stack.size() > 0){
									parent = stack.pop();
								} else {
									parent = null;
								}
								
								if(parent != null){
									if(parent.left == balancerNode) parent.left = temp;
									else if (parent.right == balancerNode) parent.right = temp;	
									
									balancerNode = parent;
								} else {
									root = temp;
									break;
								}
							}
						}
					} else if(rightHeight - leftHeight >= 2){//right side is heavier
						
						//it means node has been added in right-right subtree of balancerNode
						if(node.data.getData().compareTo(balancerNode.right.data.getData()) >= 0){
							//left rotate on position of balancer node 
							temp = balancerNode.right;
							balancerNode.right = temp.left;
							temp.left = balancerNode;
							
							//reassign height of rotated nodes
							leftHeight = temp.right.left != null ? temp.right.left.height : -1;
							rightHeight = temp.right.right != null ? temp.right.right.height : -1;
							temp.right.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
							
							leftHeight = temp.left.left != null ? temp.left.left.height : -1;
							rightHeight = temp.left.right != null ? temp.left.right.height : -1;
							temp.left.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
							
							temp.height = temp.right.height > temp.left.height ? temp.right.height + 1 : temp.left.height + 1;
							
							if(stack.size() > 0) {
								parent = stack.pop();
							} else {
								parent = null;
							}
							
							if(parent != null){
								if(parent.left == balancerNode) parent.left = temp;
								else if (parent.right == balancerNode) parent.right = temp;	
								
								balancerNode = parent;
							} else {
								root = temp;
								break;
							}
						}
						//it means node has been added in right-left subtree of balancerNode
						else if(node.data.getData().compareTo(balancerNode.right.data.getData()) <= 0){
							//right rotate on position of balancer node's right child
							temp = balancerNode.right.left;
							balancerNode.right.left = temp.right;
							temp.right = balancerNode.right;
							balancerNode.right = temp;
							
							//left rotate on position of balancer node 
							temp = balancerNode.right;
							balancerNode.right = temp.left;
							temp.left = balancerNode;
							
							//reassign height of rotated nodes
							leftHeight = temp.right.left != null ? temp.right.left.height : -1;
							rightHeight = temp.right.right != null ? temp.right.right.height : -1;
							temp.right.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
							
							leftHeight = temp.left.left != null ? temp.left.left.height : -1;
							rightHeight = temp.left.right != null ? temp.left.right.height : -1;
							temp.left.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
							
							temp.height = temp.right.height > temp.left.height ? temp.right.height + 1 : temp.left.height + 1;
							
							if(stack.size() > 0){
								parent = stack.pop();
							} else {
								parent = null;
							}
							
							if(parent != null){
								if(parent.left == balancerNode) parent.left = temp;
								else if (parent.right == balancerNode) parent.right = temp;	
								
								balancerNode = parent;
							} else {
								root = temp;
								break;
							}
						}
					} else {
						//reassign height
						leftHeight = balancerNode.left != null ? balancerNode.left.height : -1;
						rightHeight = balancerNode.right != null ? balancerNode.right.height : -1;
						balancerNode.height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
						
						//set balancer node for next iteration
						if(stack.size() > 0){
							balancerNode = stack.pop();
						} else {
							balancerNode = null;
						}
					}	
				}
			}
		}
		return root;
	}
	
	public void inorder(TreeNode<E> node, int level){
		if(node != null){
			int newLevel = level + 1;
			System.out.println(node + " * [Level = "+Integer.toString(level)+"]");
			inorder(node.left, newLevel);
			inorder(node.right, newLevel);
		}
	}
	
	public void preorder(TreeNode<E> node, int level){
		if(node != null){
			int newLevel = level + 1;
			preorder(node.left, newLevel);
			System.out.println(node + " * [Level = "+Integer.toString(level)+"]");
			preorder(node.right, newLevel);
		}
	}
	
	public void postorder(TreeNode<E> node, int level){
		if(node != null){
			int newLevel = level + 1;
			postorder(node.left, newLevel);
			postorder(node.right, newLevel);
			System.out.println(node + " * [Level = "+Integer.toString(level)+"]");
		}
	}
	
}
