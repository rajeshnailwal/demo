package practice.algorithm.code.problems;

public class FindIndexOfSublistInList {
    public static class LinkedListNode{
        String val;
        LinkedListNode next;
    
        LinkedListNode(String node_value) {
            val = node_value;
            next = null;
        }
    };
    
    public static void main(String...strings){
    	String[] str = {"1", "2", "3", "4", "5"};
    	String[] str1 = {"3","4","5"};
    	LinkedListNode head1 = null, head2 = null;
    	for(int i = 0; i < str.length; ++i){
    		head1 = _insert_node_into_singlylinkedlist(head1, str[i]);
    	}
    	
    	for(int i = 0; i < str1.length; ++i){
    		head2 = _insert_node_into_singlylinkedlist(head2, str1[i]);
    	}
    	
    	int i = find(head1, head2);
    	System.out.println(i);
    }
    
    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, String val){
        if(head == null) {
            head = new LinkedListNode(val);
        }
        else {
            LinkedListNode end = head;
            while (end.next != null) {
                end = end.next;
            }
            LinkedListNode node = new LinkedListNode(val);
            end.next = node;
        }
        return head;
    }
    
    static int find(LinkedListNode list, LinkedListNode sublist) {
        int size1 = size(list);
        int size2 = size(sublist);
        int count = size1 - size2, currentIndex = -1;
        
        if(list != null && sublist != null){
            LinkedListNode currentPointer = list;
        
            while(count >= 0){
                ++currentIndex;
                if(currentPointer.val == sublist.val){
                    boolean isFound = findSublist(currentPointer, sublist);
                    if(isFound){
                        return currentIndex;
                    }
                }
                currentPointer = currentPointer.next;
                --count;
            }
        }
        
        
        return -1;
    }

    static boolean findSublist(LinkedListNode pointer, LinkedListNode sublist){
        while(sublist != null){
            if(pointer.val == sublist.val){
                pointer = pointer.next;
                sublist = sublist.next;
            } else {
                return false;
            }            
        }
        return true;
    }

    static int size(LinkedListNode node){
        int count = 0;
        if(node != null){
            ++count;
            while((node = node.next) != null){
                ++count;
            }
        }
        return count;
    }
}
