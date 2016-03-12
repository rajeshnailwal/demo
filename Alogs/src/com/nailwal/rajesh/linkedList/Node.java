package com.nailwal.rajesh.linkedList;

import com.nailwal.rajesh.data.Data;

public class Node<E> {
	
	private Node<E> next;
	private Node<E> previous;
	private Node<E> backwardNext;
	private Node<E> forwardPrevious;
	
	private Data<E> data;

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}

	public Node<E> getBackwardNext() {
		return backwardNext;
	}

	public void setBackwardNext(Node<E> backwardNext) {
		this.backwardNext = backwardNext;
	}

	public Node<E> getForwardPrevious() {
		return forwardPrevious;
	}

	public void setForwardPrevious(Node<E> forwardPrevious) {
		this.forwardPrevious = forwardPrevious;
	}

	public Data<E> getData() {
		return data;
	}

	public void setData(Data<E> data) {
		this.data = data;
	}
	
}
