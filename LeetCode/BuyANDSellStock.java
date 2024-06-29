class BuyANDSellStock {
    public int maxProfit(int[] prices) {

      int ans = 0;
      int min = prices[0];

      for(int i = 1; i < prices.length;i++){
        
        int profit = prices[i] - min;
        
        if (profit > ans){
             ans = profit;
        }
        min = Math.min(min , prices[i]);    
      } 
      return ans;  
    }
}
