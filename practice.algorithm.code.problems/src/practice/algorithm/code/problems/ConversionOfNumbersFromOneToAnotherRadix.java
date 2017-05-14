package practice.algorithm.code.problems;

public class ConversionOfNumbersFromOneToAnotherRadix {

	public static void main(String[] args) {
		System.out.println(othersToDecimal("11001", 2));
		System.out.println(othersToDecimal("710", 8));
		System.out.println(othersToDecimal("A10", 16));
		System.out.println(decimalToOthers("100015", 16));
		System.out.println(decimalToOthers("7", 7));
	}
	
	private static String decimalToOthers(String decimal, int radix){
		int number = Integer.parseInt(decimal);
		StringBuilder num = new StringBuilder("");
		
		int division;
		
		while((division = (int)(number / radix)) != 0){
			
			int remainder = number % radix;
			
			if(radix == 16){
				switch(remainder){
					case 10 :
						num.append("A");
						break;
					case 11 :
						num.append("B");
						break;
					case 12 :
						num.append("C");
						break;
					case 13 :
						num.append("D");
						break;
					case 14 :
						num.append("E");
						break;
					case 15 :
						num.append("F");
						break;
					default :
						num.append(remainder);
				}
			} else if(radix == 7){
				switch(remainder){
					case 0 :
						num.append("0");
						break;
					case 1 :
						num.append("a");
						break;
					case 2 :
						num.append("t");
						break;
					case 3 :
						num.append("l");
						break;
					case 4 :
						num.append("s");
						break;
					case 5 :
						num.append("i");
						break;
					case 6 :
						num.append("n");
						break;
					default :
						num.append(remainder);
				}
			} else {			
				num.append(remainder);
			}
			
			number = division;
		}
		
		if(radix == 7) {
			switch(number % radix){
				case 0 :
					num.append("0");
					break;
				case 1 :
					num.append("a");
					break;
				case 2 :
					num.append("t");
					break;
				case 3 :
					num.append("l");
					break;
				case 4 :
					num.append("s");
					break;
				case 5 :
					num.append("i");
					break;
				case 6 :
					num.append("n");
					break;
			}
		} else {
			num.append(number % radix);
		}
		
		
		return num.reverse().toString();
	}
	
	private static String othersToDecimal(String binary, int radix){
		int length = binary.length();
		int num = 0;
		
		int bit;
		for(int i = 0; i < length; ++i){
			char ch = binary.charAt(length - 1 - i);
			
			if(radix == 16){
				switch(ch){
					case 'A' :
					case 'a' :
						bit = 10;
						break;
					case 'B' :
					case 'b' :
						bit = 11;
						break;
					case 'C' :
					case 'c' :
						bit = 12;
						break;
					case 'D' :
					case 'd' :
						bit = 13;
						break;
					case 'E' :
					case 'e' :
						bit = 14;
						break;
					case 'F' :
					case 'f' :
						bit = 15;
						break;
					default :
						bit = Integer.parseInt(Character.toString(ch));
				}
			} else {
				bit = Integer.parseInt(Character.toString(ch));
			}
			
			num += bit * (int)Math.pow(radix, i);
			
		}
		
		return Integer.toString(num);
	}

}