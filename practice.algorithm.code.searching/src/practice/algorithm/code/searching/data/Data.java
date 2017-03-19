package practice.algorithm.code.searching.data;

public class Data<E extends Comparable<E>> {
	private E data;
	
	public Data(E data){
		this.data = data;
	}
	
	public E getData(){
		return data;
	}
	
	public String toString(){
		return data != null ? data.toString() : "null";
	}
}
