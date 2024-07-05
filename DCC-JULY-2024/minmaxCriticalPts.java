/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class minmaxCriticalPts {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        
       ListNode prev = head;
       ListNode curr = head.next;

       int prev_pos = -1, curr_pos = -1, first_pos = -1, pos = 0;
       int[] ans = {-1 , -1};

       while(curr.next != null){  
          /* If the cuurent node next value is null then the maxima or minima cannot be found it will show error*/

          if((curr.val < prev.val/* LOCAL MINIMA */&& curr.val < curr.next.val)  /*LOCAL MINIMA */ || (curr.val > prev.val   /*LOCAL MAXIMA */&& curr.val > curr.next.val))  /*LOCAL MAXIMA*/{
 
 
      /* If the above condition satisfies then prev position assigned to curr pos value*/
               prev_pos = curr_pos;
               curr_pos = pos;
        /* CURR POS NOW WILL CONTAIN POSITION VALUE */
               if (first_pos == -1){

                  first_pos = pos;

               }             

               if(prev_pos != -1){

                  if (ans[0] == -1)

                      ans[0] = curr_pos - prev_pos;   /* MAXIMUM DISTANCE maxDist */
               
                 else 

                      ans[0] = Math.min(ans[0] , curr_pos - prev_pos);   /*MinDistance */
                     
                      ans[1] = pos - first_pos;
              /* ans[0] denoted minimum distance and ans[1] denotes maximum distance*/            
          }

       }

        pos++;
        prev = curr;
        curr = curr.next;
    
     }

     return ans;

    }

}
