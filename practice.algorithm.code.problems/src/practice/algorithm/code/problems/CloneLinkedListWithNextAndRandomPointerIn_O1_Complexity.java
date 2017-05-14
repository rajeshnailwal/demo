package practice.algorithm.code.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author rajeshnailwal
 * 
 * Given a linked list having two pointers in each node. The first one points to the next node of the list, 
 * however the other pointer is random and can point to any node of the list. Write a program that clones 
 * the given list in O(1) space, i.e., without any extra space.
 *
 */
public class CloneLinkedListWithNextAndRandomPointerIn_O1_Complexity {
	
	public static void main(String...strings){
		
		Node start = null;
		Node last = null;
		Node node = null;
		
		List<Node> list = new ArrayList<Node>();
				
		//Data creation part
		for(int i = 0; i < 10; ++i){
			Node newNode = new Node(i);
			if(start == null) start = newNode;
			else last.next = newNode;
			last = newNode;
			list.add(newNode);
		}
		
		Collections.shuffle(list);
		
		node = start;
		int i = 0;
		while(node != null) {
			node.random = list.get(i);
			node = node.next;
			++i;
		} while(node != null);
		
		
		//execution of algorithm
		cloneList(start);
		
	}
	
	public static Node cloneList(Node start){
		Node node = start, next = start.next;
		
		//Create duplicate nodes and insert them in between the nodes
		do {
			node.next = new Node(node.data);
			node.next.next = next;
			node = next;
			if(next != null){
				next = node.next;
			}
			
		}while(node != null);
		
		//Clone random pointer
		node = start;
		while(node != null){
			node.next.random = node.random.next;
			node = node.next.next;
		}
		
		//Remove temporary next pointer reference and move it to original pointer reference of original next node
		//i.e. remove nodes which are added in between original list and connect them
		node = start;
		Node clonedStart = next = node.next;
		while(node != null){
			node.next = next.next;
			node = next.next;
			
			if(next.next != null) {
				next.next = next.next.next;
			}
			
			next = next.next;
		}
		
		printList(start);
		printList(clonedStart);
		
		return null;
	}
	
	public static void printList(Node start){
		Node node = start;
		
		while(node != null){
			System.out.print(node);
			node = node.next;
		}
		System.out.println();
	}
	
	public static class Node {
		int data;
		Node next;
		Node random;
		
		public Node(int data){
			this.data = data;
		}
		
		public String toString(){
			return "[Node "+Integer.toString(data)+", next "+ (next != null ? Integer.toString(next.data) : "null") +", random "+(random != null ? Integer.toString(random.data) : "null") +"] ->";
		}
	}
	
}
