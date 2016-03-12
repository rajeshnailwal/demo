package com.nailwal.rajesh.linkedList;

public class ListUtil {
	
	public static Node createListWithNNodes(int n){
		Node node = new Node();
		Node head = node;
		
		while (--n > 0){
			node.setNext(new Node());
			node = node.getNext();
		}
		
		return head;
	}
	
	public static boolean createLoopAtNthNode(Node head, int n){
		Node nthNode = null;
		Node node = head;
		
		if(head != null){
			while (node.getNext() != null){
				if(--n == 0){
					nthNode = node;
				}			
				node = node.getNext();
			}
		}		
		
		if(nthNode != null){
			node.setNext(nthNode);
			return true;
		}
		
		return false;
	}
	
	public static void traverseAndPrintList(Node head){
		if(head != null){
			Node node = head;
			while(node != null){
				System.out.println(node);
				node = node.getNext();
			}
		}
	}
	
	public static void traverseAndPrintListUptoNthNode(Node head, int n){
		if(head != null){
			Node node = head;
			while(node != null && --n >= 0){
				System.out.println(node);
				node = node.getNext();
			}
		}
	}
}
