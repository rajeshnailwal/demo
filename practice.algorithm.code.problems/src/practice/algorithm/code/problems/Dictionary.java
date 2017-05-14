package practice.algorithm.code.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {	
	
	private static DictionaryItem root = new DictionaryItem();
	
	public static void main(String...strings) {
		String[] words = {"a","gforgeeks" , "geeksquiz" };
		for(String word : words){
			insert(root, word);
		}
		
		printAllWords(root, "");
		
		System.out.println(search(root, "a"));
		System.out.println(suggestions(root, "g"));
	}
	
	private static void insert(DictionaryItem root, String content) {
		DictionaryItem item = root;
		
		for(int i = 0; i < content.length(); ++i){
			Character ch = Character.valueOf(content.charAt(i));
			DictionaryItem next = item.getNext(ch);
			
			if(next == null){
				next = new DictionaryItem();
				item.setNext(ch, next);
			}
			
			if(i==content.length() - 1) {
				next.setIsWordEnd(true);
			}
			else item = next;
		}
	}
	
	private static boolean search(DictionaryItem item, String word){
		boolean isFound = false;
		
		for(int i = 0; i < word.length(); ++i) {
			Character ch = Character.valueOf(word.charAt(i));
			DictionaryItem next = item.getNext(ch);
			if(next != null){
				if(i == word.length() - 1) if(next.isWordEnd()) isFound = true; else break;
				item = next;				
			} else break;
		}
				
		return isFound;
	}
	
	private static List<String> suggestions(DictionaryItem item, String word){
		List<String> suggestions = new ArrayList<String>(3);
		
		for(int i = 0; i < word.length(); ++i) {
			Character ch = Character.valueOf(word.charAt(i));
			DictionaryItem next = item.getNext(ch);
			if(next != null){
				if(i == word.length() - 1) getSuggestions(next, word, suggestions);
				else item = next;				
			} else break;
		}
		
		return suggestions;
	}
	
	private static void getSuggestions(DictionaryItem item, String word, List<String> suggestions){
		if(item != null){
			if(item.isWordEnd()){
				suggestions.add(word);
			}
			
			if(suggestions.size() < 3){
				for(char ch = 'a'; ch <= 'z'; ch++){
					DictionaryItem next = item.getNext(Character.valueOf(ch));
					if(next != null) {
						getSuggestions(next, word + ch, suggestions);
					}
				}
			}
		}
	}
	
	private static void printAllWords(DictionaryItem item, String word){
		if(item != null){
			for(char ch = 'a'; ch <= 'z'; ch++){
				DictionaryItem next = item.getNext(Character.valueOf(ch));
				if(next != null) {
					if(next.isWordEnd()){
						System.out.println(word + ch);
					}
					printAllWords(next, word + ch);
				}
			}
		}
	}
	
	public static class DictionaryItem {
		private boolean isWordEnd;
		private Map<Character, DictionaryItem> nexts = new HashMap<Character, DictionaryItem>(){
			{
				for(char ch = 'a'; ch <= 'z'; ch++) {
					put(Character.valueOf(ch),null);
				}				
			}
		};
		
		public DictionaryItem getNext(Character ch){
			return nexts.get(ch);
		}
		
		public void setNext(Character ch, DictionaryItem item){
			nexts.put(ch, item);
		}
		
		public boolean isWordEnd(){
			return isWordEnd;
		}
		
		public void setIsWordEnd(boolean isWordEnd) {
			this.isWordEnd = isWordEnd;
		}
	}
	
}
