package leetcoce.array;

public class BuyAndSellStock {
    public static void main(String[] args) {
        System.out.println("Max profit (buy and sell stock once) : " +
                maxProfitByBuySellOnce(new int[] {12,15,10,14,16,18,9,25,23,15,10}));

        System.out.println("Max profit (buy and sell stock twice) : " +
                maxProfitBuySellTwice(new int[] {2,4,6,10,20,15,13,12,30,35,40}));
    }

    /**
     * Return max profit if we were to exercise buy and sell of stock only once
     *
     * @param stockPrices
     * @return int maximum profit
     */
    public static int maxProfitByBuySellOnce(int[] stockPrices) {
        if (null == stockPrices || stockPrices.length < 2) {
            return 0;
        }

        int minPriceSoFar = stockPrices[0]; // keep track of min price during each iteration
        int currentProfit = 0; // calculate currentProfit during each iteration
        int maxProfit = Integer.MIN_VALUE;

        for (int stockPrice : stockPrices) {
            minPriceSoFar = Math.min(minPriceSoFar, stockPrice); // keep track of minSoFar in the iteration
            currentProfit = stockPrice - minPriceSoFar; // calculate currentProfit based on minSoFar and current stockPrice
            maxProfit = Math.max(currentProfit, maxProfit);
        }

        return maxProfit;
    }

    /**
     * Return max profit if we were to exercise buy and sell of stock twice
     *
     * A - [2   4   6   10  20  15  13   12   30  35  40]
     * F - [0   2   4   8   18  18  18  *18*  28  33  38] // calculate the current profit with minSoFar
     * B - [38  36  34  30  28  28  28  *28*  10  5    0] // calculate the current profit with maxSoFar from back
     * T - [38  38  38  38  46  46  46  *46*  38  38  38]
     *
     * @param prices
     * @return int maximum profit
     */
    public static int maxProfitBuySellTwice(int[] prices) {
        if (null == prices || prices.length < 2) {
            return -1;
        }

        int profit = Integer.MIN_VALUE;
        int[] F = new int[prices.length];

        // Min and Max for front and back arrays
        int minSoFar = prices[0];
        int maxSoFar = prices[prices.length - 1];

        for (int i = 0; i < prices.length; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);
            profit = Math.max(profit, (prices[i] - minSoFar));
            F[i] = profit;
        }

        for (int j = prices.length - 1; j > 0; j--) {
            maxSoFar = Math.max(maxSoFar, prices[j]);
            profit = Math.max(profit, (maxSoFar - prices[j]) + F[j]);
        }

        return profit;
    }
}