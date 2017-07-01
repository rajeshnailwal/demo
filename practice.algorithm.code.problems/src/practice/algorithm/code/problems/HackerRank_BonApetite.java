package practice.algorithm.code.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackerRank_BonApetite {

	public static void main(String[] args) {
		try {
			File file = new File("resource/HackerRank_BonApetite_data.txt");
			System.setIn(new FileInputStream(file));
			Solution.main(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class Solution {

	    public static void main(String[] args) {
	    	Scanner in = new Scanner(System.in);
	    	int items = in.nextInt();
	    	int alergic = in.nextInt();
	    	
	    	int[] allItems = new int[items];
	    	for(int i = 0; i < items; ++i){
	    		allItems[i] = in.nextInt();
	    	}
	    	
	    	int cost = in.nextInt();
	    	int totalCost = 0;
	    	
	    	for(int i = 0; i < items; ++i){
	    		if(i != alergic){
	    			totalCost += allItems[i];
	    		}
	    	}	    	
	    	
	    	int share = totalCost / 2;
	    	if(share == cost){
	    		System.out.println("Bon Appetit");
	    	} else {
	    		System.out.println(Math.abs(share - cost));
	    	}
	    }
	}

}
