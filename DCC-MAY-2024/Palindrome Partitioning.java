/*
131. Palindrome Partitioning

Given a string s, partition s such that every 
substring
 of the partition is a 
palindrome
. Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Let's walkthrough🚶🏻‍♂️ the implementation process with an example for better understanding🎯:
🚶🏻‍♀️Walkthrough with s = "aab"
Initialization:

result = []
path = []
start = 0
First Call to backtrack: backtrack("aab", 0, [], result)

start = 0
Loop through end from 1 to 3.
First Iteration (end = 1):

substring = "a" (from index 0 to 0).
Check if "a" is a palindrome: isPalindrome("aab", 0, 0) returns true.
Add "a" to path: path = ["a"].
Recursive call: backtrack("aab", 1, ["a"], result)
Second Call to backtrack: backtrack("aab", 1, ["a"], result)

start = 1
Loop through end from 2 to 3.
First Iteration (end = 2):

substring = "a" (from index 1 to 1).
Check if "a" is a palindrome: isPalindrome("aab", 1, 1) returns true.
Add "a" to path: path = ["a", "a"].
Recursive call: backtrack("aab", 2, ["a", "a"], result)
Third Call to backtrack: backtrack("aab", 2, ["a", "a"], result)

start = 2
Loop through end from 3 to 3.
First Iteration (end = 3):

substring = "b" (from index 2 to 2).
Check if "b" is a palindrome: isPalindrome("aab", 2, 2) returns true.
Add "b" to path: path = ["a", "a", "b"].
Recursive call: backtrack("aab", 3, ["a", "a", "b"], result)
Fourth Call to backtrack: backtrack("aab", 3, ["a", "a", "b"], result)

start = 3, which equals s.length().
Add path to result: result = [["a", "a", "b"]].
Return from the function.
Backtrack:

Remove "b" from path: path = ["a", "a"].
Return to the previous call: backtrack("aab", 2, ["a", "a"], result).
Backtrack:

Remove "a" from path: path = ["a"].
Return to the previous call: backtrack("aab", 1, ["a"], result).
Second Iteration (end = 3):

substring = "ab" (from index 1 to 2).
Check if "ab" is a palindrome: isPalindrome("aab", 1, 2) returns false.
Continue loop.
Backtrack:

Remove "a" from path: path = [].
Return to the previous call: backtrack("aab", 0, [], result).
Second Iteration (end = 2):

substring = "aa" (from index 0 to 1).
Check if "aa" is a palindrome: isPalindrome("aab", 0, 1) returns true.
Add "aa" to path: path = ["aa"].
Recursive call: backtrack("aab", 2, ["aa"], result)
Fifth Call to backtrack: backtrack("aab", 2, ["aa"], result)

start = 2
Loop through end from 3 to 3.
First Iteration (end = 3):

substring = "b" (from index 2 to 2).
Check if "b" is a palindrome: isPalindrome("aab", 2, 2) returns true.
Add "b" to path: path = ["aa", "b"].
Recursive call: backtrack("aab", 3, ["aa", "b"], result)
Sixth Call to backtrack: backtrack("aab", 3, ["aa", "b"], result)

start = 3, which equals s.length().
Add path to result: result = [["a", "a", "b"], ["aa", "b"]].
Return from the function.
Backtrack:

Remove "b" from path: path = ["aa"].
Return to the previous call: backtrack("aab", 2, ["aa"], result).
Backtrack:

Remove "aa" from path: path = [].
Return to the previous call: backtrack("aab", 0, [], result).
Third Iteration (end = 3):

substring = "aab" (from index 0 to 2).
Check if "aab" is a palindrome: isPalindrome("aab", 0, 2) returns false.
Continue loop.
End of Function:

All iterations complete.
Return result = [["a", "a", "b"], ["aa", "b"]].
📤Final Output
The final result is:

[["a", "a", "b"], ["aa", "b"]]

*/

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        // If we've reached the end of the string, add the current partition to the result list
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // Explore all possible partitions
        for (int end = start + 1; end <= s.length(); end++) {
            // If the current substring is a palindrome, add it to the current path
            if (isPalindrome(s, start, end - 1)) {
                path.add(s.substring(start, end));
                // Recur to find other partitions
                backtrack(s, end, path, result);
                // Backtrack to explore other partitions
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        // Check if the substring s[left:right+1] is a palindrome
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
