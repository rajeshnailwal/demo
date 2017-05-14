package practice.algorithm.code.problems;

public class GetMax10NumbersOutOfMillions {
	
	static Node[] boundedMinHeap = new Node[10];
	static int spaceForEntry = 0;
	
	public static void main(String...strings){
		int[] arr = {8,5,89,435,765,3,1,87,94,24,77,31,78,32,453,224,228,324,626,34567,345,22,674,255};
		
		for(int i = 0; i < arr.length; i++){
			if((boundedMinHeap[spaceForEntry] != null && arr[i] > boundedMinHeap[0].data) //If heap is full and data to insert is larger than heap root(minimum) 
					|| boundedMinHeap[spaceForEntry] == null) { //If heap is not full
				Node node = new Node(arr[i]);
				insertInMinHeap(node);
			}			
		}
		
		for(int i = 0; i < boundedMinHeap.length; i++) System.out.println(boundedMinHeap[i]);
	}
	
	private static void insertInMinHeap(Node node){
		
		if(boundedMinHeap[spaceForEntry] != null){//it means heap is full
			int currentIndex = 0;
			boundedMinHeap[currentIndex] = node;
			
			while(currentIndex <= (boundedMinHeap.length - 1) / 2) {
				int leftChildIndex = 2 * currentIndex + 1;
				int rightChildIndex = 2 * currentIndex + 2;
				int indexToCompare = ((rightChildIndex > boundedMinHeap.length - 1) || (boundedMinHeap[leftChildIndex].data < boundedMinHeap[rightChildIndex].data)) ? leftChildIndex : rightChildIndex;
				
				if(boundedMinHeap[currentIndex].data > boundedMinHeap[indexToCompare].data) {
					Node temp = boundedMinHeap[currentIndex];
					boundedMinHeap[currentIndex] = boundedMinHeap[indexToCompare];
					boundedMinHeap[indexToCompare] = temp;
					currentIndex = indexToCompare;
				} else {
					break;
				}
			}
		} else {
			int currentIndex = spaceForEntry;
			boundedMinHeap[spaceForEntry] = node;
			while(currentIndex > 0){
				int parentIndex = currentIndex == 0 ? -1 : (currentIndex - 1) / 2;
				if(boundedMinHeap[currentIndex].data < boundedMinHeap[parentIndex].data) {
					Node temp = boundedMinHeap[parentIndex];
					boundedMinHeap[parentIndex] = boundedMinHeap[currentIndex];
					boundedMinHeap[currentIndex] = temp;
					currentIndex = parentIndex;
				} else {
					break;
				}
			}
		}
		
		spaceForEntry = (spaceForEntry == boundedMinHeap.length - 1 ? spaceForEntry : spaceForEntry + 1);
	}
	
	private static class Node {
		int data;
		int height;
		
		public Node(int data){
			this.data = data;
		}
		
		Node left;
		Node right;
		Node parent;
		
		public String toString(){
			return (left != null ? Integer.toString(left.data) : "null") + " <- " + data +" -> "+(right != null ? Integer.toString(right.data) : "null") +", parent "+(parent != null ? Integer.toString(parent.data) : "null");
		}
	}
	
}
