package practice.algorithm.code.searching.data;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<E extends Comparable<E>> {
	
	public String name;
	public Data<E> data;
	public Object additionalInfo;
	
	public List<GraphNode<E>> adjacentNodes = new ArrayList<GraphNode<E>>();/*{
		
		@Override
		public boolean contains(Object name){
			return ((name != null
					&& GraphNode.this.name != null
					&& name.getClass().getName().equals("java.lang.String")
					&& ((String)name).equals(GraphNode.this.name))
					|| name == GraphNode.this.name);
		}
	};*/
	
	
	public GraphNode(String name, Data<E> data){
		this.name = name;
		this.data = data;
	}	
	
	public GraphNode(String name, Data<E> data, Object additionalInfo){
		this.name = name;
		this.data = data;
		this.additionalInfo = additionalInfo;
	}
	
	public int hashCode(){
		return name != null ? name.hashCode() : 0;
	}
	
	public boolean equals(Object object){
		return object != null 
				&& object.getClass().getName().equals(this.getClass().getName())
				&& ((((GraphNode)object).name != null && this.name != null && ((GraphNode)object).name.equals(this.name))
						|| ((GraphNode)object).name == this.name); 
	}
	
	public String toString(){
		return name;
	}
	
}
