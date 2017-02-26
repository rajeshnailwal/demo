package practice.algorithm.code.sorting;

/**
 * QuickSort is a Divide and Conquer algorithm. Philosophy of this algorithm is to choose an element as pivot
 * and fix its position in a way that all bigger number are after this number and all smaller numbers are 
 * before this number in array. So it will be the actual position of element in sorted array.
 * 
 *  1. In this algorithm we first select a pivot element
 *  2. Pivot element can be either first or last element from array
 *  3. Here we are considering pivot as last element
 *  4. Choose position of first element of array
 *  5. Starting with this element we compare it with pivot
 *  6. If element is smaller than pivot we increase position of element by 1 to pick next element 
 *     to compare and then repeat step 5
 *  7. If element is greater or equal to pivot
 *  	7a. We move the element just one position left to pivot to the position of first element.
 *  	7b. We move the pivot just one position left to its current position
 *  	7c. We move the element (earlier it was first element) which was compared with pivot to 
 *  		earlier position of pivot (earlier position, as position of pivot is changed in step 6b.)
 *  	7d. We then mark new position of pivot, reducing it by 1
 *  8.	Repeat step 5
 *  9. After completing this iteration we will find that all numbers left of pivot are smaller than
 *     pivot and all number in right are greater or equal to pivot. 
 *  10.Now divide array into two parts first part will contain all elements on left of the pivot element
 *     and second part will contain all elements on right of the pivot element
 *  11.Repeat step 3 with both new arrays
 *  12.Repeat step 3 to 11 until you are left with one single element in each array 
 *  
 * @author Rajesh
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1062, 9083, 1118, 6, 9533, 7837, 2117, 5645, 902386, 430247, 31269, 634525, 8728, 89473, 67490, 768098, 56783923, 8, 4, 10, 345};
		
		long t1 = System.nanoTime();
		sort(arr, 0, arr.length - 1);
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		
		for(int i = 0; i < arr.length; ++i){
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void sort(int[] arr, int start, int end){
		if(arr != null && start < end && start >= 0 && end >= 0){
			//choose last indexed element as pivot
			int pivotPosition = end;
			int pointer = start;
			
			while(pointer != pivotPosition){
				if(arr[pointer] < arr[pivotPosition]){
					++pointer;
				} else {
					int pointedElement = arr[pointer];
					//move element previous(just left) to pivot to position of pointer
					arr[pointer] = arr[pivotPosition - 1];
					//move pivot to previous(just left) of its current position
					arr[pivotPosition - 1] = arr[pivotPosition];
					//move pointed element which was earlier pointed by pointer to the position where pivot was
					arr[pivotPosition] = pointedElement;
					--pivotPosition;
				}
			}
			
			//the pivot element is now on its position as it would have been in the sorted state
			
			//now repeat the same process with all elements left of pivot
			sort(arr, start, pivotPosition - 1);
			
			//now repeat the same process with all elements right of pivot
			sort(arr, pivotPosition + 1, end);
		}
	}

}
