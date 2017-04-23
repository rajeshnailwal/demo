package practice.algorithm.code.datastructures.data;

public class TreeNode {
	
	public Data data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	
	public TreeNode(Data data){
		this.data = data;
	}
	
	public TreeNode(Data data, TreeNode left, TreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public TreeNode(Data data, TreeNode left, TreeNode right, TreeNode parent){
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
