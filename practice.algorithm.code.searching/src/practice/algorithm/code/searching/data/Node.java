package practice.algorithm.code.searching.data;

public class Node<E extends Comparable<E>> {
	public Data<E> data;
	public Node<E> left;
	public Node<E> right;
	
	public int height = -1;
	
	public Node(Data<E> data){
		this.data = data;
	}
	
	public String toString(){
		return "[left="+left+", node="+this+", right="+right+"]";
	}
}
