package practice.algorithm.code.sorting;

/**
 * RadixSort is a non-comparative sorting algorithm that sorts data with integer keys by grouping keys by the individual
 * digits which share the same significant position and value.
 * 
 * 1. Sort By LSD : Radix sort by LSD is done by starting from the least significant digit (LSD) i.e. right most digit and moving
 * 					to most significant digit. 
 * 					1. Starting with Least Significant Digit (LSD). This digit works as a key which is used to assign 
 * 					   number to the respective bucket.
 * 					2. We create 10 buckets each represents a respective key from 0 to 9.
 * 					3. Now we select first number.
 * 					4. Get its corresponding digit and move this number to the representing bucket.   
 * 					5. Select next number and go to step 3 until all numbers from array are inserted into respective buckets.
 * 					   (For the numbers having same digit we maintain the sequence of number in the bucket same as that in
 * 					    original array.)
 * 					6. Once all numbers are distributed in buckets we merge all numbers from all buckets starting from first bucket 
 * 					   i.e. key 0 to last bucket i.e. key 9. 
 * 					7. Now we move to the next digit. i.e. the digit before the last selected digit (which is LSD first time).
 * 					   and go to 2. This is done until all numbers are in a single bucket.
 * 2. Sort By MSD : Same as Sort By LSD but it flows from MSD to LSD
 * 3. First improvement : In this we first distribute numbers on the basis of their sizes.
 * 					1. Create array of buckets. The key for each bucket is the size of number. 
 * 					   (For example number 123 is of size 3 and number 56738 is of size 5)
 * 					2. Select first number.
 * 					3. Get its size.
 * 					4. Assign it to representing bucket.
 * 					5. Select next number and go to step 3 until all numbers are distributed in buckets.
 * 					6. Now select first bucket as input array.
 * 					7. Sort this input array using Sort By LSD.
 * 					8. Select next bucket and go to 7 until all buckets are sorted
 * 					9. Merge these buckets in sequence from first to last in order to get sorted array.
 * 4. Sort By Shift Operator :
 * 					In this we use shift operator and use divide and conquer technique to sort array.
 * 					1. We use two pointers start pointer = 0 and end pointer = arr.length - 1.
 * 					2. Start with first bit which is most significant bit (left most bit) of a number.
 * 					3. Select first number and get its corresponding bit.
 * 					4. If bit is set i.e. 1 then swap the number to the number at end pointer and then 
 * 					   reduce end pointer by 1.
 * 					5. If bit is not set i.e. 0 then increase start pointer by 1.
 * 					6. Continue till start pointer = end pointer
 * 					7. when start pointer = end pointer check for 4th and 5th condition and accordingly 
 * 					   and accordingly divide array in two parts and perform the same steps from 3 to 7 
 * 					   on the two divided arrays using bit next to the current selected bit (next means 
 * 					   from left to right).
 * 					8. Continue 3 to 7 till you reach least significant bit.
 * 
 * @author Rajesh
 *
 */

public class RadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1062, 9083, 1118, 6, 9533, 7837, 2117, 5645, 902386, 430247, 31269, 634525, 8728, 89473, 67490, 768098, 56783923, 8, 4, 10, 345};
		long t1 = System.nanoTime();
		//arr = sortByLSD(arr);
		//arr = improvementOnSortByLSD(arr);
		arr = sortByInplaceShiftUsingMSD(arr);
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		for(int i : arr){
			System.out.print(i+",");
		}
	}
	
	
	public static int[] sortByLSD(int[] arr){
		if(arr != null){
			int divisor = 10;
			
			int[][] buckets = null;
			do {
				buckets = createBuckets(arr, divisor);
				if(buckets != null){
					//distributeInBuckets(buckets, arr, divisor);
					mergeBuckets(buckets, arr);
					divisor *= 10;
				}
			}while(buckets != null);
			
		}
		return arr;
	}
	
	public static int[] improvementOnSortByLSD(int[] arr){
		if(arr != null && arr.length > 0){
			int divisor = 10;	
			int[][] sizeBuckets = createSizeBuckets(arr, divisor);
						
			if(sizeBuckets != null){
				for(int i = 0; i < sizeBuckets.length; ++i){
					int[] bucket = sizeBuckets[i];
					if(bucket != null && bucket.length > 1){
						sizeBuckets[i] = sortByLSD(bucket);
					}
				}
				
				int index = 0;
				for(int i = 0; i < sizeBuckets.length; ++i){
					int[] bucket = sizeBuckets[i];
					if(bucket != null && bucket.length > 0){
						for(int j = 0; j < bucket.length; ++j){
							arr[index] = bucket[j];
							++index;
						}
					}
				}
			}
		}
		return arr;
	}
	
	public static int[] sortByInplaceShiftUsingMSD(int[] arr){
		sort(arr, 0, arr.length - 1, 31);
		return arr;
	}
	
	public static void sort(int[] arr, int start, int end, int maskedBit){
		if(maskedBit >= 0){
			int shift = maskedBit - 1;
			int mask = 1 << shift;
			int pointer1 = start;
			int pointer2 = end;
			
			while(pointer1 < pointer2){
				int bitPositionValue = arr[pointer1] & mask;
				if(bitPositionValue > 0){//it means this bit is a set bit i.e. this bit is '1'
					arr[pointer1] = arr[pointer2] + arr[pointer1] - (arr[pointer2] = arr[pointer1]);
					--pointer2;
				} else {//it means this bit is not set bit i.e. this bit is '0'
					++pointer1;
				}
			}
			
			if(pointer1 == pointer2){
				int bitPositionValue = arr[pointer1] & mask;
				if(bitPositionValue > 0){//it means this bit is a set bit i.e. this bit is '1'
					sort(arr, start, pointer1 - 1, shift);
					sort(arr, pointer1, end, shift);
				}  else {//it means this bit is not set bit i.e. this bit is '0'
					sort(arr, start, pointer1, shift);
					sort(arr, pointer1 + 1, end, shift);
				}
			}
		}
	}
	public static int[][] createBuckets(int[] arr, int divisor){
		int[] bucketSizes = new int[10];
		int[][] buckets = new int[10][];
		int[] elementToBucket = new int[arr.length];
		
		for(int i = 0; i < arr.length; ++i){
			elementToBucket[i] = (arr[i] % divisor) /(divisor / 10); 
			bucketSizes[elementToBucket[i]] += 1;
		}
		
		for(int i = 0; i < bucketSizes.length; ++i){
			if(arr.length == bucketSizes[i]){
				return null;
			} else {
				buckets[i] = new int[bucketSizes[i]];
			}
		}
		
		distributeInBuckets(buckets, elementToBucket, arr, divisor);
		
		return buckets;
	}
	
	public static void distributeInBuckets(int[][] buckets, int[] arr, int divisor){
		int[] fillIndex = new int[10];
		
		for(int element : arr){
			int i = ((element % divisor) / (divisor / 10));
			buckets[i][fillIndex[i]] = element;
			fillIndex[i] += 1;
		}
	}
	
	public static void distributeInBuckets(int[][] buckets, int[] elementToBucket, int[] arr, int divisor){
		int[] fillIndex = new int[10];
		
		for(int i = 0; i < arr.length; ++i){
			buckets[elementToBucket[i]][fillIndex[elementToBucket[i]]] = arr[i];
			fillIndex[elementToBucket[i]] += 1;			
		}
	}
	
	public static int[][] createSizeBuckets(int[] arr, int divisor){
		int size = 1, maxSize = size;		
		//elementToSize is a mapping of array element to the size of element
		int[] elementToSize = new int[arr.length];
		//bucketSizes is an array of count of elements in each bucket where index of bucketSizes represent size of element
		int[] bucketSizes;
		//buckets is collection of various buckets where each bucket represents collection of elements of the size represents by the index of buckets 
		int[][] buckets;
		
		//This calculates the size of each element
		for(int i = 0; i < arr.length; ++i){
			elementToSize[i] = size = getSize(arr[i]);
			if(size > maxSize){
				maxSize = size;
			}
		}
		
		buckets = new int[maxSize][];
		bucketSizes = new int[maxSize];
		
		for(int i = 0; i < arr.length; ++i){
			bucketSizes[elementToSize[i] - 1] += 1;
		}
		
		for(int i = 0; i < arr.length; ++i){
			int sizeToIndex = elementToSize[i] - 1;
			int[] bucket = buckets[sizeToIndex];
			if(bucket == null){
				bucket = new int[bucketSizes[sizeToIndex]];
				buckets[sizeToIndex] = bucket;
			}
			
			bucket[bucket.length - bucketSizes[sizeToIndex]] = arr[i]; 
			bucketSizes[sizeToIndex] -= 1;
		}
		
		return buckets;
	}
	
	public static int getSize(int num){
		int divisor = 10, size = 1;
		
		while(num / divisor > 0){
			size++;
			divisor *= 10;
		}
		
		return size;
	}
	
	public static void mergeBuckets(int[][] buckets, int[] arr){
		int k = 0;
		for(int i = 0; i < buckets.length; ++i){
			int[] bucketEntries = buckets[i];
			if(bucketEntries.length > 0){
				for(int j = 0; j < bucketEntries.length; ++j){
					arr[k] = bucketEntries[j];
					++k;
				}
			}
		}
	}
	
}
