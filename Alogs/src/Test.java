import java.util.ArrayList;
import java.util.Scanner;


public class Test {

	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sequences = in.nextInt();
        int count = in.nextInt();
        int[][] queries = new int[count][3];
        for(int i = 0; i < count; ++i){
            for(int j = 0; j < 3; ++j){
                queries[i][j] = in.nextInt();
            }
        }
        queryProcessing(sequences, queries);
    }
	
	private static void queryProcessing(int sequenceCount, int[][] queries){
        int lastans = 0;
        ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>(sequenceCount);
        
        for(int i = 0; i < sequenceCount; ++i){
            sequences.add(new ArrayList<Integer>());
        }
        
        for(int[] query : queries){
            ArrayList<Integer> sequence = sequences.get((query[1] ^ lastans) % sequenceCount);
            if(query[0] == 1){
                sequence.add(query[2]);
            } else if(query[0] == 2){
            	if(sequence.size() > 0){
            		int i = sequence.get(query[2] % sequence.size());
                    System.out.println(i);
                    lastans = i;
            	}
            }
        }
        
    }

}
