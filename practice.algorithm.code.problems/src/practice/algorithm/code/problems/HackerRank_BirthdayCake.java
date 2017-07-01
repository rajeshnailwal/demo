package practice.algorithm.code.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackerRank_BirthdayCake {

	public static void main(String[] args) {
		try {
			Solution.main(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class Solution {

	    static int solve(int n, int[] s, int d, int m){
	        int total = 0;
	        if(s.length >= m){
	            for(int i = 0; i <= s.length - m; ++i){
	                int sum = 0;
	                for(int j = i; j < i + m; ++j){
	                    sum += s[j];
	                }
	                
	                if(sum == d) ++total;
	            }
	        }
	        return total;
	    }

	    public static void main(String[] args) throws FileNotFoundException {
	    	File file = new File("resource/HackerRank_BirthdayCake_data.txt");
			System.setIn(new FileInputStream(file));
	    	
	        Scanner in = new Scanner(System.in);
	        int n = in.nextInt();
	        int[] s = new int[n];
	        for(int s_i=0; s_i < n; s_i++){
	            s[s_i] = in.nextInt();
	        }
	        int d = in.nextInt();
	        int m = in.nextInt();
	        int result = solve(n, s, d, m);
	        System.out.println(result);
	    }
	}


}
