package practice.algorithm.code.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackerRank_DayOfTheProgrammer {

	public static void main(String[] args) {
		try {
			File file = new File("resource/HackerRank_DayOfTheProgrammer_data.txt");
			System.setIn(new FileInputStream(file));
			Solution.main(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class Solution {

	    static String solve(int year){
	        String date = Integer.toString(year);
	        
	        //cumulative number days each month in non-leap year
	        //Jan 31, Feb 59, Mar 90, Apr 120, May 151, Jun 181, Jul 212, Aug 243, Sep 273, Oct 304, Nov 334, Dec 365
	        //cumulative number days each month in leap year
	        //Jan 31, Feb 60, Mar 91, Apr 121, May 152, Jun 182, Jul 213, Aug 244, Sep 274, Oct 305, Nov 335, Dec 366

	        //get 256th day which will be a day of september
	        date = "09."+date;
	        int dt = 0;
	        if(year < 1918){
	        	if(year % 4 == 0) dt = 256 - 244;
	        	else dt = 256 - 243;
	        } else if(year > 1918){
	        	if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) dt = 256 - 244;
	        	else dt = 256 - 243;
	        } else {
	        	dt = 256 - 243 + 13;
	        }
	        date = dt + "."+ date;
	        return date;
	    }

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int year = in.nextInt();
	        String result = solve(year);
	        System.out.println(result);
	    }
	}


}
