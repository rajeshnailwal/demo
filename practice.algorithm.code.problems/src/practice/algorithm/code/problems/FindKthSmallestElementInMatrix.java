package practice.algorithm.code.problems;

/**
 * 
 * @author rajeshnailwal
 * 
 * Find Kth smallest element in 2D Matrix where both column and row sorted in increasing order
 * 
 * 		10, 20, 30, 40
 *      15, 25, 35, 45
 *      24, 29, 37, 48
 *      32, 33, 39, 50
 *
 */
public class FindKthSmallestElementInMatrix {

	public static void main(String[] args) {
		Node[][] matrix = {
							{new Node(10,0,0), new Node(20,0,1), new Node(30,0,2), new Node(40,0,3)},
							{new Node(15,1,0), new Node(25,1,1), new Node(35,1,2), new Node(45,1,3)},
							{new Node(24,2,0), new Node(29,2,1), new Node(37,2,2), new Node(48,2,3)},
							{new Node(32,3,0), new Node(33,3,1), new Node(39,3,2), new Node(50,3,3)},
						  };
		
		System.out.println(find(matrix, 13).data);
		
	}
	
	private static Node find(Node[][] matrix, int k){
		Node[] heap = new Node[4];
		Node[] auxArray = new Node[k];
		Node dummy = new Node(Integer.MAX_VALUE, 4,4);
		int pos = 0;
		
		for(int i = 0; i < 4; ++i){
			insertInHeap(heap, matrix[0][i], pos);
			pos = ++pos % heap.length; 
		}
		
		int j=k;
		while(--k >= 0){
			Node smallestNodeInHeap = heap[0];
			auxArray[(auxArray.length - 1) - k] = smallestNodeInHeap;
			
			if(smallestNodeInHeap.positionX + 1 < matrix.length){
				Node node = matrix[smallestNodeInHeap.positionX + 1][smallestNodeInHeap.positionY];
				insertInHeap(heap, node, pos);
			} else {
				Node node = dummy;
				insertInHeap(heap, node, pos);
			}
			
		}
		
		return auxArray[j-1];
	}
	
	private static void insertInHeap(Node[] heap, Node node, int pos){
		
		int pointer = pos;
		
		if(heap[pos] == null){
			heap[pos] = node;
			int parent = (pointer - 1) / 2;
			Node temp = null;
			
			while(parent > 0){
				if(heap[parent].data > heap[pointer].data){
					temp = heap[parent];
					heap[parent] = heap[pointer];
					heap[pointer] = temp;
					pointer = parent;
					parent = (pointer - 1) / 2;
				} else {
					break;
				}
			}
		} else {
			heap[pos] = node;//pos will always be zero
			Node temp = null;
			
			while(pointer <= (heap.length - 1)/2){
				int childToCompare = 2*pointer + 1;
				childToCompare = (2*pointer + 2 <= heap.length - 1) && heap[childToCompare].data > heap[2*pointer + 2].data ? 2*pointer + 2 : childToCompare;
				
				if(heap[pointer].data > heap[childToCompare].data){
					temp = heap[pointer];
					heap[pointer] = heap[childToCompare];
					heap[childToCompare] = temp;
					pointer = childToCompare;
				} else {
					break;
				}
			}
		}
	}
	
	private static class Node {
		int data;
		int positionX;
		int positionY;
		
		public Node(int data, int positionX, int positionY){
			this.data = data;
			this.positionX = positionX;
			this.positionY = positionY;
		}
	}

}
