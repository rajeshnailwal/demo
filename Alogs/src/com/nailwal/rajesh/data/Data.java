package com.nailwal.rajesh.data;

public class Data<E> {
	private E e;
	
	public Data(E e){
		this.e = e;
	}
	
	public E getData(){
		return e;
	}
	
	public void setData(E e){
		this.e = e;
	}
	
	public String toString(){
		return e.toString();
	}
}
