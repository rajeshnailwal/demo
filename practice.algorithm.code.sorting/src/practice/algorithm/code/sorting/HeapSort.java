package practice.algorithm.code.sorting;

public class HeapSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1062, 9083, 1118, 6, 9533, 7837, 2117, 5645, 902386, 430247, 31269, 634525, 8728, 89473, 67490, 768098, 56783923, 8, 4, 10, 345};;
		long t1 = System.nanoTime();
		arr = sort(arr);
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		for(int i : arr){
			System.out.print(i+",");
		}
	}
	
	public static int[] sort(int[] arr){
		if(arr != null){
			for(int i = 0; i < arr.length; ++i){
				arr = maxHeapify(arr, arr.length - i);
				int index = arr.length - 1 - i;
				//Move first element which will be largest element (as it is a root of max heap) to arr.length - 1 - i
				arr[0] = arr[index] + arr[0] - (arr[index] = arr[0]);
			}
		}
		return arr;
	}
	
	/**
	 * This method converts the portion of an array into max heap. The portion of the array which is to 
	 * be converted into heap starts from index 0 to index heapSize - 1
	 * 
	 * @param arr
	 * @param heapSize is the size of heap (i.e. range of array from starting index 0 to end index heapSize - 1)
	 * @return
	 */
	public static int[] maxHeapify(int[] arr, int heapSize){
		
		if(arr != null){
			//select the last parent of a candidate heap array
			//and compare it with its child and swap parent with
			//bigger child and do the same till we reach root
			int parent = (heapSize / 2) - 1;
			int leftChild, rightChild, largest;
			
			while(parent >= 0) {
				leftChild = 2 * parent + 1;
				rightChild = 2 * parent + 2;
				largest = parent;
				
				if(leftChild < heapSize && arr[leftChild] > arr[largest]){
					largest = leftChild;
				}
				
				if(rightChild < heapSize && arr[rightChild] > arr[largest]) {
					largest = rightChild;
				}
				
				if(largest != parent){
					arr[largest] = arr[parent] + arr[largest] - (arr[parent] = arr[largest]);
				}
				
				--parent;
			}
			
		}
		
		return arr;
	}

}
