package com.nailwal.rajesh.data;

public class TreeNode<E> {

	public TreeNode<E> left;
	public TreeNode<E> right;
	public TreeNode<E> parent;
	public Data<E> data;
	public int height;
	public int size;

	public TreeNode(Data<E> data){
		this.data = data;
	}

	public TreeNode(){}

}

