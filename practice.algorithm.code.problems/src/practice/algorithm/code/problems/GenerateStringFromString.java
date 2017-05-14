package practice.algorithm.code.problems;

public class GenerateStringFromString {
	
	public static void main(String...strings) {
		read("332456119", 5);
	}
	
	public static String read(String str){
		
		String newstr = "";
		char last = str.charAt(0);
		int count = 1;
		
		for(int i = 1; i < str.length(); ++i) {
			if(last == str.charAt(i)){
				count++;
			} else {
				newstr += count;
				newstr += last;
				last = str.charAt(i);
				count = 1;
			}
		}
		
		newstr += count;
		newstr += last;
		
		System.out.println(newstr);
		return newstr;
	}
	
	public static void read(String str, int num) {
		//System.out.println(str);
		for(int i = 0; i < num; ++i) {
			str = read(str);
		}
	}
	
}
