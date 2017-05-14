package practice.algorithm.code.problems;

public class Rotate2DArray {

	public static void main(String[] args) {
		int[][] _2D_NxN_Array = new int[][]{
			{5,8,56,98,78},
			{89,72,987,2,1},
			{9,565,7,10,88},
			{91,90,3,16,12},
			{71,13,17,55,77}
		};
		
		rotate180(_2D_NxN_Array);
	}
	
	public static void rotateClockwise90(int[][] _2D_NxN_Array){
		boolean[][] check = new boolean[5][5];
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
		
		int col_ind = 0, row_ind = 0;
		for(int i = 0; i < _2D_NxN_Array.length * _2D_NxN_Array.length; ++i){
			row_ind = i / _2D_NxN_Array.length;
			col_ind = i % _2D_NxN_Array.length;
			
			int num = _2D_NxN_Array[row_ind][col_ind];
			int swapsup;
			while(!check[row_ind][col_ind]) {
				check[row_ind][col_ind] = true;
				swapsup = _2D_NxN_Array[col_ind][(_2D_NxN_Array.length - 1) - row_ind];
				_2D_NxN_Array[col_ind][(_2D_NxN_Array.length - 1) - row_ind] = num;
				num = swapsup;
				
				swapsup = row_ind;
				row_ind = col_ind;
				col_ind = (_2D_NxN_Array.length - 1) - swapsup;
			}
		}
		
		System.out.println("==========");
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void rotateAntiClockwise90(int[][] _2D_NxN_Array){
		boolean[][] check = new boolean[5][5];
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
		
		int col_ind = 0, row_ind = 0;
		for(int i = 0; i < _2D_NxN_Array.length * _2D_NxN_Array.length; ++i){
			row_ind = i / _2D_NxN_Array.length;
			col_ind = i % _2D_NxN_Array.length;
			
			int num = _2D_NxN_Array[row_ind][col_ind];
			int swapsup;
			while(!check[row_ind][col_ind]) {
				check[row_ind][col_ind] = true;
				swapsup = _2D_NxN_Array[(_2D_NxN_Array.length - 1) - col_ind][row_ind];
				_2D_NxN_Array[(_2D_NxN_Array.length - 1) - col_ind][row_ind] = num;
				num = swapsup;
				
				swapsup = row_ind;
				row_ind = (_2D_NxN_Array.length - 1) - col_ind;
				col_ind = swapsup;
			}
		}
		
		System.out.println("==========");
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void rotate180(int[][] _2D_NxN_Array){
		boolean[][] check = new boolean[5][5];
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
		
		int col_ind = 0, row_ind = 0;
		for(int i = 0; i < _2D_NxN_Array.length * _2D_NxN_Array.length; ++i){
			row_ind = i / _2D_NxN_Array.length;
			col_ind = i % _2D_NxN_Array.length;
			
			int num = _2D_NxN_Array[row_ind][col_ind];
			int swapsup;
			while(!check[row_ind][col_ind]) {
				check[row_ind][col_ind] = true;
				swapsup = _2D_NxN_Array[(_2D_NxN_Array.length - 1) - row_ind][(_2D_NxN_Array.length - 1) - col_ind];
				_2D_NxN_Array[(_2D_NxN_Array.length - 1) - row_ind][(_2D_NxN_Array.length - 1) - col_ind] = num;
				num = swapsup;
				
				row_ind = (_2D_NxN_Array.length - 1) - row_ind;
				col_ind = (_2D_NxN_Array.length - 1) - col_ind;
			}
		}
		
		System.out.println("==========");
		
		for(int i = 0; i <_2D_NxN_Array.length; ++i){
			for(int j = 0; j < _2D_NxN_Array.length; ++j){
				System.out.print(_2D_NxN_Array[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
