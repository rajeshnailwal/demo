package practice.algorithm.code.sorting;

/**
 * SelectionSort is a simple sorting algorithm. 
 * 
 *  1. In this algorithm we pick a number from index i (starting with i = 0)
 *  2. We then compare it with all subsequent number after this and find number 
 *     which is least smallest to the picked number
 *  3. Swap the picked number with the number least smallest than the picked number 
 *  4. Increase index by 1 and go to step 2
 *  
 * @author Rajesh
 *
 */
public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{8,5,2,6,9,3,3,1,4,0,7};
		
		arr = sort(arr);
		
		for(int i : arr){
			System.out.print(i+",");
		}
	}
	
	public static int[] sort(int[] arr){
		if(arr != null){
			for(int i = 0; i < arr.length; ++i){
				int smallerIndex = i;
				int element = arr[i];
				for(int j = i + 1; j < arr.length; ++j){
					if(arr[smallerIndex] > arr[j]){
						smallerIndex = j;
					}
				}
				arr[i] = arr[smallerIndex];
				arr[smallerIndex] = element;
			}
		}
		return arr;
	}
}