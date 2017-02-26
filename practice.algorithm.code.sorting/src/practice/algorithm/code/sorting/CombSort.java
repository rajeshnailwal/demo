package practice.algorithm.code.sorting;

/**
 * CombSort is a generalization of bubble or insertion sort. The basic idea is to eliminate turtles, 
 * or small values near the end of the list, since in a bubble sort these slow the sorting down tremendously.
 * In a normal bubble or insertion sort we use gap sequence of 1. This algorithm calculates gaps using a 
 * shrink factor. Gap is recalculated by dividing it with the shrink factor after each iteration. Initial value
 * of the gap is the entire length of array. We sort sub arrays on the basis of gap calculated (same as that in 
 * shell sort). A Gap sequence is an equal distance among the elements of array which are sorted. Let's say 5 is
 * a gap sequence so the indeces of elements of array with the gap sequence 5 are 0, 5, 10, 15, 20,.....so the sub
 * array which is sorted choosing a gap sequence of 5 using bubble or insertion sort will be having elements 
 * represented by these indeces.  
 * 
 * 
 * @author Rajesh
 *
 */
public class CombSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{62,83,18,53,07,17,95,86,47,69,25,28,1};
		
		arr = sort(arr);
		
		for(int i : arr){
			System.out.print(i+",");
		}
	}
	
	public static int[] sort(int[] arr){
		if(arr != null){
			double shrinkFactor = 1.3;
			int gap = arr.length - 1;
			while(gap > 0) {
				sort(arr, gap);
				gap /= shrinkFactor;
			};
		}
		return arr;
	}
	
	public static int[] sort(int[] arr, int gap){
		//iteration ends at (< arr.length - gap) as the second last element of sub-array will be before index (arr.length - gap)
		//in actual array for selected gap
		for(int i = 0; i < arr.length - gap; i += gap){
			//iteration ends at (< (arr.length - gap) - i) as all elements after this sub-array will be already sorted
			for(int j = 0; j < (arr.length - gap) - i; j += gap){
				if(arr[j + gap] < arr[j]){
					arr[j + gap] = (arr[j + gap] + arr[j]) - (arr[j] = arr[j + gap]);
				}
			}
		}
		return arr;
	}
}
