package practice.algorithm.code.problems;

/**
 * 
 * @author rajeshnailwal
 * 
 * The cost of a stock on each day is given in an array, find the max profit 
 * that you can make by buying and selling in those days. For example, if the 
 * given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can 
 * earned by buying on day 0, selling on day 3. Again buy on day 4 and sell 
 * on day 6. If the given array of prices is sorted in decreasing order, then 
 * profit cannot be earned at all.
 *
 */
public class StockBuySellToMaximizeProfit {
	
	
	public static void main(String...strings){
		//Data creation part
		int[] stockPrices = new int[]{45,4,89,23,98,456,84,34,1234,567,234,21,9};
		
		boolean isBuy = true;
		int currentPrice = stockPrices[0];
		int lastBuy = 0;
		
		for(int i = 1; i < stockPrices.length; ++i){
			int nextPrice = stockPrices[i];
			if(isBuy) {
				if(nextPrice > currentPrice) {
					System.out.println("Buy @ "+currentPrice+" on day "+(i));  
					lastBuy = currentPrice;
					isBuy = false;
				}
				currentPrice = stockPrices[i];
			} else {
				if(nextPrice < currentPrice){
					System.out.println("Sell @ "+currentPrice+" on day "+(i));
					isBuy = true;
				}
				currentPrice = stockPrices[i];
			}
		}
		
		if(lastBuy < currentPrice){
			System.out.println("Sell @ "+currentPrice+" on day "+(stockPrices.length));
		}
	}
	
}
