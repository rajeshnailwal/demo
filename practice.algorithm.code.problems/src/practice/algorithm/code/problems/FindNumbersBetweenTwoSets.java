package practice.algorithm.code.problems;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FindNumbersBetweenTwoSets {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("resource/FindNumbersBetweenTwoSets_data.txt");
		System.setIn(new FileInputStream(file));
		
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] b = new int[m];
        for(int b_i=0; b_i < m; b_i++){
            b[b_i] = in.nextInt();
        }        
        
        int lcm = getLCM(a);
        int hcf = getHCF(b);
        
        System.out.println(lcm);
        System.out.println(hcf);
        
        int count = 0, increment = 1;
       
        if(hcf % lcm == 0){
        	int divisor = lcm * increment;
        	while(divisor <= hcf){
        		
        		if(hcf % divisor == 0) count++;
        		
        		divisor = lcm * ++increment;
        	}
        }
        
        System.out.println(count);
        
    }
	
	private static int getLCM(int[] arr){
		arr = Arrays.copyOf(arr, arr.length);
    	int lcm = 1;
    	
    	boolean isDivisibleBy2 = false;
    	do {
    		isDivisibleBy2 = false;
    		for(int i = 0; i < arr.length; ++i){
    			if(arr[i] % 2 == 0){
    				arr[i] /= 2;
    				isDivisibleBy2 = true;
    			}
    		}
    		
    		if(isDivisibleBy2) lcm *= 2;
    	}while(isDivisibleBy2);
    	
    	boolean isDivisibleByi = false;
    	int limit = (int)Math.sqrt(Arrays.stream(arr).max().getAsInt());    	
    	
    	for(int i = 3; i <= Arrays.stream(arr).max().getAsInt() && !checkForAllOnes(arr); ){
    		isDivisibleByi = false;
    		for(int j = 0; j < arr.length; ++j){
    			if(arr[j] % i == 0){
    				arr[j] /= i;
    				isDivisibleByi = true;
    			}
    		}
    		
    		if(isDivisibleByi) lcm *= i;
    		else i += 2;
    	}
    	
    	if(!checkForAllOnes(arr)){
    		for(int i = 0; i < arr.length; ++i){
    			lcm *= arr[i];
    		}
    	}
    		
        return lcm;
    }
	
	private static int getHCF(int[] arr){
		int hcf = 1;
		
		boolean isDivisibleBy2 = false;
    	do {
    		isDivisibleBy2 = false;
    		for(int i = 0; i < arr.length; ++i){
    			if(arr[i] % 2 == 0){
    				arr[i] /= 2;
    				isDivisibleBy2 = true;
    			} else {
    				isDivisibleBy2 = false;
    				break;
    			}
    		}
    		
    		if(isDivisibleBy2) hcf *= 2;
    	}while(isDivisibleBy2);    	
		
		boolean isDivisibleByi = false;
    	int limit = (int)Math.sqrt(Arrays.stream(arr).max().getAsInt());
    	
		for(int i = 3; i <= Arrays.stream(arr).max().getAsInt() && !checkForAllOnes(arr); ){
    		isDivisibleByi = false;
    		for(int j = 0; j < arr.length; ++j){
    			if(arr[j] % i == 0){
    				arr[j] /= i;
    				isDivisibleByi = true;
    			} else {
    				isDivisibleByi = false;
    				break;
    			}
    		}
    		
    		if(isDivisibleByi) hcf *= i;
    		else i += 2;
    	}
		return hcf;
	}
    
    private static boolean checkForAllOnes(int[] arr){
        for(int i : arr){
            if(i!=1) return false;
        }
        return true;
    }

}
