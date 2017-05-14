package practice.algorithm.code.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author rajeshnailwal
 * 
 * 
 * A third-party provider uses a custom data format to exchange information with us. We need to validate the input before we process it further.
 * The character encoding is US-ASCII. Valid characters are all the characters between 0x20 (space) and 0x7E (~).
 * All fields are delimited by '|', and '~' is the escape character. There are only three valid escape sequences:
 *  '~|' stands for '|'
 *  '~~' stands for '~'
 *  '~n' stands for new line
 * 
 * A line represents one record, it must start and end with '|'.
 * The first line contains the field names. The remaining lines contain the records. Names can't be empty and must be unique, there is no restriction on values.
 * If a record has more fields than there are names defined, the last name defined will be used and '_#' will be appended to the field name 
 * where # is the number of extra record starting at 1.
 * 
 * Here is a valid example:
 *
 * |name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie||annie@test.com|~n
 * 
 * This represents the following data:
 *
 * name       address             address_1
 * Patrick    patrick@test.com    pat@test.com
 * Annie                          annie@test.com
 *       
 * Valid input should output statistics about the data: number of records, number of fields, number of empty values and the name of the last field. 
 * For the previous example, the following output is expected: "2:3:1:address_1"
 *
 * Invalid input (such as '~~~') should output the following message "0:0:0:format_error"
 * You have to write a validate function which verifies the input string conforms to the requirements and generates either the expected output or the error message.
 *
 */
public class CustomDataFormat {
	
	public static void main(String...strings){
		String input = "|name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie||annie@test.com|~n";
		input = "|name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie|annie@test.com|~n|Zoe|~n";
		
		String output = validate(input);
		
		System.out.println(output);
		
	}
	
	public static String validate(String input){
		boolean isValid = false;
		
		String[] splits = input.split("~n");
		
		String output = "";
		
		List<String> fields = new ArrayList<String>();
		Map<String, ArrayList<String>> table = new HashMap<String, ArrayList<String>>();
		int emptycount = 0;
		
		if(splits != null && splits.length > 0){
			
			
			isValid = checkForUniquenessNonEmptyFieldNames(splits[0], fields);
			
			if(isValid){
				String lastFieldName = fields.get(fields.size() - 1);
				int originalFieldCount = fields.size();
				
				for(String field : fields){
					table.put(field, new ArrayList<String>());
				}
				
				if(isValid){
					for(int i = 1; i < splits.length; ++i){
						int ec = validateAndFetchRecords(splits[i], fields, table, lastFieldName, originalFieldCount);		
						if(ec == -1){
							isValid = false;
							break;
						} else {
							emptycount += ec;
						}
					}
				}
			}			
		}
		
		if(isValid) output = table.get(fields.get(0)).size()+":"+fields.size()+":"+emptycount+":"+fields.get(fields.size() - 1);
		else output = "0:0:0:format_error";
		
		return output;
	}
	
	private static boolean validateRow(String line){
		boolean isValid = true;
		
		isValid = isValid && terminationCheck(line);
		isValid = isValid && charRangeCheck(line);
		
		return isValid;
	}
	
	private static boolean checkForUniquenessNonEmptyFieldNames(String line, List<String> names){
		boolean isValid = validateRow(line);
		
		if(isValid){
			line = line.substring(1, line.length());
			line = line.substring(0, line.length() - 1);
			String[] fields = line.split("(?<!~)\\|");
			
			for(int j = 0; j < fields.length; ++j){
				String field = fields[j];
				field = field.replaceAll("\\~\\|", "|");
				isValid = checkValidEscapeCharacters(field);
				if(!isValid) break; 
				field = field.replaceAll("\\~\\~", "~");
				
				if(names.contains(field) || field.isEmpty()){
					isValid = false;
					break;
				} else {
					names.add(field);
				}
				
			}			
		}		
		
		return isValid;
	}
	
	private static int validateAndFetchRecords(String line, List<String> fields, Map<String, ArrayList<String>> table, String lastFieldName, int originalFieldCount){
		boolean isValid = validateRow(line);
		int spacecount = 0;
		
		if(isValid){
			line = line.substring(1, line.length());
			line = line.substring(0, line.length() - 1);
			String[] values = line.split("(?<!~)\\|");
			
			if(values != null){
				int size = table.get(table.keySet().iterator().next()).size();
				
				if(values.length > table.size()) {
					int start = table.size() - originalFieldCount;
					int end = values.length - originalFieldCount;
					
					for(int i = start + 1; i <= end; ++i){
						String newField = lastFieldName+"_"+i;
						
						ArrayList<String> list = new ArrayList<String>();
						
						table.put(newField, list);
						
						//add empty values
						for(int j = 0; i < size; ++i){
							list.add("");
							++spacecount;
						}
						fields.add(newField);
					}
				}
				
				for(int j = 0, i = 0; j < values.length; ++j, ++i){
					String value = values[j];
					value = value.replaceAll("\\~\\|", "|");
					isValid = checkValidEscapeCharacters(value);
					if(!isValid) break; 
					value = value.replaceAll("\\~\\~", "~");
					
					List<String> ls = table.get(fields.get(i));
					ls.add(value);
					if(value.isEmpty()) ++spacecount;
				}
				
				//add empty values
				if(isValid && values.length < fields.size()){
					for(int i = values.length; i < fields.size(); ++i){
						List<String> ls = table.get(fields.get(i));
						ls.add("");
						++spacecount;
					}
				}
			}
		}
		
		return isValid ? spacecount : -1;
	}
	
	private static boolean terminationCheck(String line){
		return !line.isEmpty() && line.charAt(0) == '|' && line.charAt(line.length() - 1) == '|';
	}
	
	private static boolean charRangeCheck(String line){
		boolean isValid = true;
		int start = 0x20, end = 0x7E;
		
		for(int i = 0; i < line.length(); ++i){
			int ascii = line.charAt(i);
			
			if(ascii < start || ascii > end){
				isValid = false;
				break;
			}
		}
		
		return isValid;
	}
	
	private static boolean checkValidEscapeCharacters(String line){
		boolean isValid = true;
		char lastChar = '\0';
		
		for(int i = 0;i < line.length(); ++i){
			char ch = line.charAt(i);
			if(lastChar == '~'){
				if(ch == '~') {
					++i;
					lastChar = line.charAt(i);
				} else {
					isValid = false;
					break;				
				}
			} else {
				lastChar = ch;
			}
		}
		return isValid;
	}
	
	
}
