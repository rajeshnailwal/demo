package practice.algorithm.code.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackerRank_MigratoryBird {

	public static void main(String[] args) {
		try {
			File file = new File("resource/HackerRank_MigratoryBird_data.txt");
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
	        int[] types = new int[n];
	        for(int types_i=0; types_i < n; types_i++){
	            types[types_i] = in.nextInt();
	        }
	        
	        int[] totalTypeCount = new int[5];
	        for(int type : types){
	        	totalTypeCount[type - 1] += 1;
	        }
	        
	        int maxTotalType = 0;
	        for(int i = 0; i < totalTypeCount.length; ++i){
	        	if(totalTypeCount[i] > totalTypeCount[maxTotalType]) maxTotalType = i;
	        }
	        
	        System.out.println(maxTotalType+1);
	    }
	}

}
