package practice.algorithm.code.sorting;

/**
 * BubbleSort is a simple sorting algorithm. Philosophy of this algorithm is based on bubbles behavior 
 * where lighter bubbles try to move up and heavier bubbles go down
 * 
 *  1. In this algorithm we pick a number at index 0 i.e. index i = 0 (starting with i = 0)
 *  2. We then compare it with next number 
 *  	2a. If next number is smaller than picked number then swap the two numbers
 *  3. Increase index by 1 i.e. index = index + 1 and go to step 2
 *  4. Go to step 1 till the process has been iterated n number of times where n is the length of array 
 *  
 * @author Rajesh
 *
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1062, 9083, 1118, 6, 9533, 7837, 2117, 5645, 902386, 430247, 31269, 634525, 8728, 89473, 67490, 768098, 56783923, 8, 4, 10, 345};
		
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
			for(int i = 0; i < arr.length - 1; ++i){
				for(int j = 0; j < (arr.length - 1) - i; ++j){
					if(arr[j] > arr[j + 1]){
						//swap
						arr[j] = arr[j] + arr[j + 1] - (arr[j + 1] = arr[j]);
					}
				}
			}
		}
		return arr;
	}

}
