package practice.algorithm.code.datastructures.data;

public class RedBlackTreeNode {
	public enum COLOR {RED, BLACK, DOUBLE_BLACK};
	
	public COLOR color = COLOR.RED;
	
	public Data data;
	public RedBlackTreeNode left;
	public RedBlackTreeNode right;
	public RedBlackTreeNode parent;
	
	public RedBlackTreeNode(Data data){
		this.data = data;
	}
	
	public RedBlackTreeNode(Data data, RedBlackTreeNode left, RedBlackTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public RedBlackTreeNode(Data data, RedBlackTreeNode left, RedBlackTreeNode right, RedBlackTreeNode parent){
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
