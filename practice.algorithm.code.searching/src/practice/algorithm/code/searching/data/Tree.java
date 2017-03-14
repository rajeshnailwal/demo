package practice.algorithm.code.searching.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Tree<E extends Comparable<E>> {
	
	public Node<E> getCompleteBinaryTree(Data<E>[] arr) {
		List<Node<E>> list = new ArrayList<Node<E>>(arr.length);
		Node<E> node = null;
		
		Arrays.stream(arr).forEach(data -> {
			list.add(new Node<E>(data));
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
	
	public Node<E> getBinraySearchTree(Data<E>[] arr) {
		List<Node<E>> list = new ArrayList<Node<E>>(arr.length);
		Node<E> root = null;
		
		Arrays.stream(arr).forEach(data -> {
			list.add(new Node<E>(data));
		});
		
		
		for(Node<E> node : list){
			if(root == null){
				root = node;
				root.height = 0;
			} else {
				
			}
		}
		
		return root;
	}
	
	public Node<E> addNodeInBST(Node<E> root, Node<E> node){
		if(root == null){
			root = node;
			root.height = 0;
		} else {
			Node<E> nd = root;
			Node<E> parent = null;
			
			Stack<Node<E>> stack = new Stack<Node<E>>();
			
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
			
			if(nd == null){
				if(node.data.getData().compareTo(parent.data.getData()) < 0){
					parent.left = node;
					node.height = stack.size();
				} else if(node.data.getData().compareTo(parent.data.getData()) > 0){
					parent.right = node;
					node.height = stack.size();
				}
			}
		}
		return root;
	}
	
}
