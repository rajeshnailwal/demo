package practice.algorithm.code.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class HackerRank_DivisibleSumPair {

	public static void main(String[] args) {
		
		try {
			File file = new File("resource/HackerRank_DivisibleSumPair_data.txt");
			System.setIn(new FileInputStream(file));
			Solution.main(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class Solution {

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int n = in.nextInt();
	        int k = in.nextInt();
	        int[] a = new int[n];
	        for(int a_i=0; a_i < n; a_i++){
	            a[a_i] = in.nextInt();
	        }
	        
	        heapSort(a);
	        //1 1 2 2 3 6
	        
	        int count = divisibleSumPair(k, a);
	        
	        System.out.println(count);
	    }
	    
	    public static void heapSort(int[] arr){
	    	for(int i = arr.length - 1; i > 0; i--){
	    		maxHeapify(arr, i);
	    		arr[0] = arr[i] + arr[0] - (arr[i] = arr[0]);
	    		//printArray(arr);
	    	}
	    }
	    
	    
	    public static void maxHeapify(int[] arr, int lastIndex){
	    	int parent = (lastIndex - 1) / 2, leftChild, rightChild, biggerChild;
	    	
	    	while(parent >= 0){
	    		leftChild = 2*parent+1;
	    		rightChild = 2*parent+2;
	    		
	    		biggerChild = ((rightChild > lastIndex) || (arr[leftChild] > arr[rightChild])) ? leftChild : rightChild;
	    		
	    		if(arr[biggerChild] > arr[parent]) arr[parent] = arr[parent] + arr[biggerChild] - (arr[biggerChild] = arr[parent]);
	    		
	    		parent--;
	    	}
	    }
	    
	    public static int divisibleSumPair(int k, int[] a){
	    	int count = 0;
	    	for(int i = a.length - 1; i >= 0; --i){
	        	for(int j = i - 1; j >= 0 && a[i] + a[j] >= k; --j){
	        		if((a[i] + a[j]) % k == 0) ++count; 
	        	}
	        }
	    	return count;
	    }
	    
	    public static void printArray(int[] a){
	    	for(int i : a){
	        	System.out.print(i+", ");
	        }
	        System.out.println();
	    }
	}

}
