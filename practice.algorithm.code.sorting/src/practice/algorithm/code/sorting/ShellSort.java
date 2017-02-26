package practice.algorithm.code.sorting;

/**
 * ShellSort is a generalization of bubble or insertion sort. In a normal bubble or insertion sort we use gap sequence of 1.
 * This algorithm uses varying gap sequences. We sort array starting from highest gap sequence to lowest gap sequence.
 * A Gap sequence is an equal distance among the elements of array which are sorted. Let's say 5 is a gap sequence
 * so the indeces of elements of array with the gap sequence 5 are 0, 5, 10, 15, 20,.....so the sub array which is 
 * sorted choosing a gap sequence of 5 using bubble or insertion sort will be having elements represented by these indices.  
 * 
 * 
 * @author Rajesh
 *
 */
public class ShellSort {

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
			//Shell sequence = [500, 250, 125, 62, 31, 15, 7, 3, 1]			FLOOR(N/2k), then for N = 1000
			//Pratt sequence 2p3q or [1, 2, 3, 4, 6, 8, 9, 12, …]
			//Knuth sequence (3k – 1) / 2 or [1, 4, 14, 40, 121, …]
			//Ciura sequence [1, 4, 10, 23, 57, 132, 301, 701, 1750, …]get further sequence by multiplying 2.25 in next terms
			int [] sequence = new int[]{1, 4, 23, 57, 132, 301, 701, 1750};//Ciura sequence
			int k = 0;
			//select maximum possible sequence
			for(int i = 0; i < sequence.length; ++i){
				if(sequence[i] < arr.length){
					k = i;
				} else {
					break;
				}
			}
			
			//sort sub-array (of arr) whose elements are that of the original array separated by a gap (sequence[k])
			do {
				arr = sort(arr, sequence[k]);
			}while(--k >= 0);//get next sequence
		}
		return arr;
	}
	//generalization of insertion sort
	public static int[] sort(int[] arr, int gap){
		if(arr != null){
			//Use insertion sort or bubble sort with the gap...We are using insertion sort
			int pointer = 0;
			int num = -1;
			
			for(int i = gap; i < arr.length; i += gap){
				pointer = i;
				num = arr[pointer];
				
				while(pointer > 0){
					if(num <= arr[pointer - gap]){
						arr[pointer] = arr[pointer -= gap];
					} else {
						break;
					}
				}
				
				arr[pointer] = num; 
			}
		}
		return arr;
	}
}
