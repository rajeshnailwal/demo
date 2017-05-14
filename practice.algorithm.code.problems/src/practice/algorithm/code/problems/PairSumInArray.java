package practice.algorithm.code.problems;

public class PairSumInArray {

	public static void main(String[] args) {
		int[] arr = {20,2,3,4,5,6,7,8,9,11,10,12,13,14,15,16,17,18,19,1, 23};
		System.out.println(countPairSums(arr));
	}
	
	private static int countPairSums(int[] arr){
		int count = 0;
		
		quickSort(arr, 0, arr.length - 1);
		
		for(int i = 0; i < arr.length; ++i){
			System.out.print(arr[i]+", ");
		}
		
		int sumInd = arr.length - 1;
		while(sumInd > 1) {
			int j = sumInd, i = 0;
			
			while(j > i){
				int sum = arr[i] + arr[j];
				
				if(sum > arr[sumInd]){
					--j;
				} else if(sum < arr[sumInd]){
					++i;
				} else {
					++count;
					++i;
					--j;
				}
			}
			
			--sumInd;
		}		
		return count;
	}
	
	private static void quickSort(int[] arr, int i, int j){
		if(i < j){
			int k = divide(arr, i, j);
			quickSort(arr, i, k-1);
			quickSort(arr, k+1, j);
		}
	}
	
	private static int divide(int[] arr, int i, int j){		
		if(i != j){
			while(i < j){
				if(arr[j-1] < arr[j]) {
					arr[i] = arr[j-1] + arr[i] - (arr[j-1] = arr[i]);
					++i;
				} else {
					arr[j] = arr[j-1] + arr[j] - (arr[j-1] = arr[j]);
					--j;
				}
			}
		}		
		return j;
	}

}
