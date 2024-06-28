class MaxSubArr {
    public int maxSubArray(int[] nums) {

      int curr_sum = nums[0];
      int max_so_far = nums[0];

      for(int i = 1; i < nums.length; i++){

         if(curr_sum < 0)

            curr_sum = 0;          
     
        curr_sum = curr_sum + nums[i];

        if (curr_sum > max_so_far){

            max_so_far = curr_sum;
        }
      }  
      
      return max_so_far;     
    
    }
}
