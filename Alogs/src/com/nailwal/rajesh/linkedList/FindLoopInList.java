package com.nailwal.rajesh.linkedList;

public class FindLoopInList {
	
	//////////////////////////////////////////////////////
	//                     L - L - L - L 	   		 	//
	//					  /				 \			 	//
	//					 /				  \				//
	//  L - L - L - L - L 				   L			//
	//					 \				  /				//
	//					  \			     /				//
	//						L - L - L - L 				//
	//////////////////////////////////////////////////////
	
	private static int LIST_SIZE = 14;
	private static Node head = ListUtil.createListWithNNodes(LIST_SIZE);
	
	static {
		ListUtil.createLoopAtNthNode(head, 5);
	}
	
	public static void main(String...strings){
		ListUtil.traverseAndPrintListUptoNthNode(head, LIST_SIZE);
		
		Node fastPointer = isLoopExist(head);		
		
		if(fastPointer != null){
			Node lastNodeInLoop = findLastNodeInLoop(head, fastPointer);
			Node loopingNode = lastNodeInLoop.getNext();
		}
	}
	
	// Floyd's Cycle Detection Algorithm
	public static Node isLoopExist (Node head){
		Node fastPointer = head, slowPointer = head;
		
		if(head != null){
			do{				
				if(fastPointer.getNext() != null && fastPointer.getNext().getNext() != null){
					fastPointer = fastPointer.getNext().getNext();
				} else {
					return null;
				}
				
				/*if(fastPointer == slowPointer){
					break;
				}*/
				
				slowPointer = slowPointer.getNext();
				
			} while (fastPointer != slowPointer);
		}
		
		return fastPointer;
	}
	
	public static Node findLastNodeInLoop(Node slowPointer, Node fastPointer){
		if(slowPointer != null && fastPointer != null){
			while(fastPointer != slowPointer){
				slowPointer = slowPointer.getNext();
				fastPointer = fastPointer.getNext();
			}
		}
		return fastPointer;
	}
	
	public static void removeLinkFromNextNode(Node node){
		node.setNext(null);
	}
}
