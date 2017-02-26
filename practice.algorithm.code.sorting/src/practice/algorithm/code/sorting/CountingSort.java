package practice.algorithm.code.sorting;

/**
 * CountingSort sort is an algorithm for sorting a collection of objects according to keys that are small integers.
 * It operates by counting the number of objects that have each distinct key value, and using arithmetic on those 
 * counts to determine the positions of each key value in the output sequence. Its running time is linear in the 
 * number of items and the difference between the maximum and minimum key values, so it is only suitable for direct 
 * use in situations where the variation in keys is not significantly greater than the number of items.
 * 
 * In summary, the algorithm loops over the items, computing a histogram of the number of times each key occurs within
 * the input collection. It then performs a prefix sum computation (a second loop, over the range of possible keys) 
 * to determine, for each key, the starting position in the output array of the items having that key. Finally, it 
 * loops over the items again, moving each item into its sorted position in the output array.
 * 
 * @author Rajesh
 *
 */
public class CountingSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{3,4,5,5,6,9,3,4,5,6,3,5,3,4,5,6,9,7,7,8,3,7,8,9,12,13,12};
		
		arr = sort(arr);
		
		printArray(arr);
		
	}
	
	public static int[] sort(int[] arr){
		if(arr != null){
			
			int smallestValue = getSmallestValue(arr);
			int largestValue = getLargestValue(arr);
			
			//this array is used to store the total number of objects to respective key
			int[] count = new int[Math.abs((largestValue - smallestValue)) + 1];
			
			//count number of time each key appears
			for(int i = 0; i < arr.length; ++i){
				int key = getKey(smallestValue, arr[i]);
				count[key] += 1;
			}
			
			//keyStartingIndex is the variable that is used to count the successive indices to respective
			//key for entering their corresponding value in sorted array.
			int keyStartingIndex = 0;
			
			//temporary variable that stores the total number of objects of a key
			int currentKeyCount = 0;
			
			//make a cumulative sequence for each key. Sequence of a key represents the position available for 
			//an object represented by that key in the final sorted array
			for(int i = 0; i < count.length; ++i){
				currentKeyCount = count[i];
				count[i] = keyStartingIndex;
				keyStartingIndex += currentKeyCount;
			}
			
			int[] output = new int[arr.length];
			
			//get the key of an object, get the position where the value for that key can be stored, 
			//assign value to that position, increase position by 1
			for(int i = 0; i < arr.length; ++i){
				int key = getKey(smallestValue, arr[i]);
				output[count[key]] = arr[i];
				count[key] += 1;
			}
			
			arr = output;
		}
		return arr;
	}
	
	public static int getSmallestValue(int[] arr){
		int key = Integer.MAX_VALUE;
		if(arr != null && arr.length > 0){
			
			for(int obj : arr){
				key = obj < key ? obj : key;
			}
			
		}
		return key;
	}
	
	public static int getLargestValue(int[] arr){
		int key = Integer.MIN_VALUE;
		if(arr != null && arr.length > 0){
			
			for(int obj : arr){
				key = obj > key ? obj : key;
			}
			
		}
		return key;
	}
	
	//Key deriving logic can be anything but here I chose key as the index of array 
	//and array represents the values from smallest to largest in the array and hence
	//the array is of the size of difference between the smallest and largest value
	//so smallest value's index in the array will be 0 and largest value's index in the
	//array will be arr.length - 1
	public static int getKey(int smallestKey, int object){
		return Math.abs(object - smallestKey);
	}
	
	public static void printArray(int[] arr){
		for(int i : arr){
			System.out.print(i+",");
		}
		System.out.println();
	}

}
