package practice.algorithm.code.problems;


/**
 * 
 * @author rajesh nailwal
 * 
 * The idea is to randomly pick a pivot element. To implement randomized partition, we use a random function, rand() to generate index between l and r, 
 * swap the element at randomly generated index with the last element, and finally call the standard partition process (same as of quick sort)
 * which uses last element as pivot.
 *
 */
public class FindKthSmallestElement {
	
	public static void main(String...str){
		int[] arr = {5,8,2,89,23,65,1,9,45,67,12,7,10,56,3,33};
		//find 10th smallest number
		System.out.println(find(arr, 10));
	}
	
	private static int find(int[] arr, int k){
		
		int start = 0, end = arr.length - 1, pointer = start, pivot = end;
		
		do {
			
			pointer = start;
			pivot = end;
			
			while(pointer != pivot){
				
				if(arr[pointer] < arr[pivot]){
					++pointer;
				} else {
					//swap pointer with element before pivot
					arr[pointer] = arr[pointer] + arr[pivot - 1] - (arr[pivot - 1] = arr[pointer]);
					//swap pivot with new element before pivot
					arr[pivot] = arr[pivot] + arr[pivot - 1] - (arr[pivot - 1] = arr[pivot]);
					--pivot;
				}
				
			}
			
			if(k - 1 == pivot){
				break;
			} else if(k - 1 < pivot){
				end = pivot - 1;
			} else if(k - 1 > pivot){
				start = pivot + 1;
			}		
			
		}while(pivot != k-1);	
		
		return arr[k-1];
	}
	
}
