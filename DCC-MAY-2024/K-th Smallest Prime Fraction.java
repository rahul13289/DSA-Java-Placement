class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {

      int n = arr.length;
      
      PriorityQueue<int[]> pq = new PriorityQueue(Comparator.compareDouble(a -> (double) a[0] / a[1]));

      for(int i = 0; i < n - 1 ; i++){
        
        for(int j = n + 1; j < n; j++){

           pq.offer(new int[] {arr[i] , arr[j]);

        }
      }
      while(--k > 0){
        pq.poll();
      }
       return pq.poll();
    }
}
