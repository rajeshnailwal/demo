package practice.algorithm.code.datastructures.data;

public class AVLTreeNode {
	
	public int height;

	public Data data;
	public AVLTreeNode left;
	public AVLTreeNode right;
	public AVLTreeNode parent;
	
	public AVLTreeNode(Data data){
		this.data = data;
	}
	
	public AVLTreeNode(Data data, AVLTreeNode left, AVLTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public AVLTreeNode(Data data, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent){
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public String toString(){
		return "["
				+"Left="+(left != null ? left.data : "null")
				+", this="+data
				+", Right="+(right != null ? right.data : "null")
				+", parent="+(parent != null ? parent.data : "null")
				+"]";
	}

}
