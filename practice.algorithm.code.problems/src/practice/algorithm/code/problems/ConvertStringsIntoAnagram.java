package practice.algorithm.code.problems;

public class ConvertStringsIntoAnagram {

	public static void main(String[] args) {
		String str1 = "dhjeodjfhiejdmvuqnzjsifn";
		String str2 = "iojdiejdnsjklfidhj";
		
		int[] arr1 = new int[26];
		int[] arr2 = new int[26];
		
		for(int i = 0; i < str1.length(); ++i) {
			arr1[str1.charAt(i) - 'a'] += 1;
		}
		
		for(int i = 0; i < str2.length(); ++i) {
			arr2[str2.charAt(i) - 'a'] += 1;
		}
		
		for(int i = 0; i < 26; ++i){
			if(arr1[i] > arr2[i]){
				arr1[i] = arr1[i] - arr2[i];
				arr2[i] = 0;
			} else if(arr1[i] < arr2[i]) {
				arr2[i] = arr2[i] - arr1[i];
				arr1[i] = 0;
			} else {
				arr1[i] = arr2[i] = 0;
			}
		}
		
		int i = 0;
		while(i < str1.length()){
			int ind = str1.charAt(i) - 'a';
			if(arr1[ind] > 0) {
				str1 = str1.substring(0, i) + str1.substring(i + 1, str1.length());
				arr1[ind] -= 1;
			} else {
				++i;
			}
		}
		
		i = 0;
		while(i < str2.length()){
			int ind = str2.charAt(i) - 'a';
			if(arr2[ind] > 0) {
				str2 = str2.substring(0, i) + str2.substring(i + 1, str2.length());
				arr2[ind] -= 1;
			} else {
				++i;
			}
		}
	}

}
