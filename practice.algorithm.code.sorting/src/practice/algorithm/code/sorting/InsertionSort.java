package practice.algorithm.code.sorting;

/**
 * InsertionSort is a simple sorting algorithm. The philosophy of this algorithm is
 * sort array while inserting any element in array
 * 
 *  1. In this algorithm we pick a number from index i (starting with i = 0)
 *  2. We then compare it with number before it, let's say number at index1 i.e. index1 = i-1
 *  3. a. If number at index1 is smaller than the picked number we write picked number at index1 + 1
 *     b. Increase i by 1 i.e. i = i + 1 and go to step 1
 *  4. a. If number at index1 is greater than the picked number we write number(number of index1) into the index1 + 1
 *     b. Reduce index1 by 1 i.e. index1 = index1 - 1 and go to 2
 *  
 * @author Rajesh
 *
 */
public class InsertionSort {

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
			for(int i = 1; i < arr.length; ++i){
				int numToInsert = arr[i], j = i;
				
				while(j > 0){
					if(numToInsert < arr[j - 1]){
						arr[j] = arr[j - 1];
					} else {
						break;
					}
					--j;
				}
				
				arr[j] = numToInsert;
			}
		}
		
		return arr;
	}

}