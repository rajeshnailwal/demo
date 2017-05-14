package practice.algorithm.code.problems;

public class FindStartingIndexOfSubStringInAString {

	public static void main(String[] args) {
		String string = "This is the hat which I don't like";
		String substring = "at which";
		
		int index = -1;
		
		for(int i = 0; i < string.length() - substring.length(); ++i){
			if(string.charAt(i) == substring.charAt(0)){
				index = find(i, string, substring);
				
				if(index > 0) break;
			}
		}
		
		System.out.println(index);
	}
	
	private static int find(int startingIndex, String string, String substring){
		int index = -1;
		int endIndex = startingIndex + substring.length() - 1;
		if(endIndex <= string.length() - 1){
			int loopTerminationIndex = (int)Math.ceil((endIndex - startingIndex) / 2);
			index = startingIndex;
			for(int i = 0; i < loopTerminationIndex; ++i){
				char ssch = string.charAt(startingIndex + i);
				char sssch = substring.charAt(i);
				char sech = string.charAt(endIndex - i);
				char ssech = substring.charAt(substring.length() - 1 - i);
				
				if(!((ssch == sssch) && (sech == ssech))){
					index = -1;
					break;
				}
			}
		} 
		
		return index;
	}

}
