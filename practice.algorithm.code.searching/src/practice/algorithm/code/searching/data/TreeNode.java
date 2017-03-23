package practice.algorithm.code.searching.data;

public class TreeNode<E extends Comparable<E>> {
	public Data<E> data;
	public TreeNode<E> left;
	public TreeNode<E> right;
	
	public int height = -1;
	
	public TreeNode(Data<E> data){
		this.data = data;
	}
	
	public String toString(){
		return "[left="+(left != null ? left.data.toString() : "null") +", node="+this.data.toString()+", right="+(right != null ? right.data.toString() : "null") +"]";
	}
}
