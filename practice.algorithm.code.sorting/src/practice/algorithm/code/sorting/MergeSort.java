package practice.algorithm.code.sorting;

/**
 * MergeSort is a Divide and Conquer algorithm
 * 
 *  1. In this algorithm we first divide array into two sub-arrays
 *  2. Continue step 1 till we get length 1 sub-arrays after each division
 *  3. Now sort and merge two latest or nearest sub-arrays into one array
 *  4. Continue step 3 till we sort and merge all sub-arrays and sort and merge the merged sub-arrays 
 *  
 * @author Rajesh
 *
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{6,5,3,1,8,7,2,4};
		arr = divide(arr, 0, arr.length - 1);
		
		for(int i : arr){
			System.out.print(i+",");
		}
	}
	
	public static int[] divide(int[] arr, int start, int end){
		
		if(arr != null){
			
			//Logic to divide array into two equal part
			int end1 = (start + end) / 2;
			int start1 = ((start + end) / 2) + 1;
			
			//division is done till we get single length array
			if(start < end -1) {
				divide(arr, start, end1);
				divide(arr, start1, end);
			}
			
			arr = merge(arr, start, end1, start1, end);
		}
		
		return arr;
	}
	
	public static int[] merge(int[] arr, int start1, int end1, int start2, int end2){
		if(arr != null){
			//combined size of the two arrays to be merged
			int size = (end1 - start1) + (end2 - start2) + 2;
			int[] tempArr = new int[size];
			int pointer1 = start1, pointer2 = start2;
			
			while(size > 0){				
				if(arr[pointer1] < arr[pointer2]) {
					tempArr[tempArr.length - size] = arr[pointer1];
					++pointer1;
				} else {
					tempArr[tempArr.length - size] = arr[pointer2];
					++pointer2;
				}
				--size;
				
				if(pointer1 > end1 || pointer2 > end2) break;
			}
			
			//if all elements of first array are sorted and merged then sort and merge elements of second array
			if(pointer1 > end1){
				while(size > 0){
					tempArr[tempArr.length - size] = arr[pointer2];
					++pointer2;
					--size;
				}
			} 
			
			//if all elements of second array are sorted and merged then sort and merge elements of first array
			if(pointer2 > end2){
				while(size > 0){
					tempArr[tempArr.length - size] = arr[pointer1];
					++pointer1;
					--size;
				}
			}
			
			//insert temporary array into original array
			int pointer = start1;
			size = (end1 - start1) + (end2 - start2) + 2;
			
			while(pointer <= end2){
				arr[pointer] = tempArr[pointer - start1];
				++pointer;
			}
		}
		
		return arr;
	}
}
