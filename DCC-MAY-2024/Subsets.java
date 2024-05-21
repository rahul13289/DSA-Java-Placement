/*
78. Subsets

Given an integer array nums of unique elements, return all possible 
subsets(the power set). The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]

📥Input: nums = [1, 2, 3].

📚Step-by-Step Walkthrough:

Initially, the result list contains only the empty subset, [].
We iterate over each element in nums.
For the first element 1:
We clone the existing subsets in result, which is currently [].
We add 1 to each cloned subset and add them back to result. So, result = [[], [1]].
For the second element 2:
We clone the existing subsets in result, which are [[], [1]].
We add 2 to each cloned subset and add them back to result. So, result = [[], [1], [2], [1, 2]].
For the third element 3:
We clone the existing subsets in result, which are [[], [1], [2], [1, 2]].
We add 3 to each cloned subset and add them back to result. So, result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]].
After iterating through all elements in nums, the result contains all subsets of [1, 2, 3], including the empty subset.
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        
      List<List<Integer>> result = new ArrayList<>();
      result.add(new ArrayList<>());

      for(int num : nums){

        int size = result.size();
        
        for(int i = 0; i < size; i++){

            List<Integer> subset = new ArrayList<>(result.get(i));
            
            subset.add(num);
            result.add(subset);

        }
      }
      
      return result;

    }
}
