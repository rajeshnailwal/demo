package practice.algorithm.code.problems;

/**
 * 
 * @author rajeshnailwal
 * 
 * Find an element in an unsorted array such that all left elements are smaller and all right elements are greater. 
 * O(n) time complexity solution is required.
 *
 */
public class PlaceAnElementSuchThatAllLeftAreSmallAndAllRightAreLarge {
	
	public static void main(String...strings) {
		int[] arr = {5,8,2,89,23,65,1,9,45,23,67,12,7,10,56,3,33};
		//5,8,2,3,23,10,1,9,7,23,12,33,67,45,56,65,89,
		placeNumber(arr);
	}
	
	public static void placeNumber(int[] arr){
		int i = 0, j = arr.length - 1;
		
		while(i != j){
			if(arr[i] < arr[j]){
				++i;
			} else {
				arr[j-1] = arr[j-1] + arr[i] - (arr[i]=arr[j-1]);
				arr[j-1] = arr[j-1] + arr[j] - (arr[j]=arr[j-1]);
				--j;
			}
		}
		
		for(int k = 0; k < arr.length; ++k) System.out.print(arr[k]+","); 
	}
	
}
