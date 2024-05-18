/*

979. Distribute Coins in Binary Tree

You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.

In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.

Return the minimum number of moves required to make every node have exactly one coin.

Example 1:

Input: root = [3,0,0]

Output: 2
  
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

SOLUTION 

Interviewer and Interviewee Discussion
Interviewer:
You are given the root of a binary tree with n nodes where each node has some coins. There are exactly n coins in total throughout the whole tree. In one move, you can choose two adjacent nodes and move one coin from one to the other. A move may be from parent to child, or from child to parent. Your task is to find the minimum number of moves required to make every node have exactly one coin.

Here is an example:

Input: root = [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Do you have any initial thoughts on how to approach this problem?

Interviewee:
Yes, I do. Initially, we can consider a brute-force approach to understand the nature of the problem. Later, we can look into optimizing it.

Brute Force Approach
Interviewee:
Let's start with the brute-force approach. In this problem, we need to ensure that every node ends up with exactly one coin. A simple, yet inefficient way to solve the problem would be to simulate the process by iterating multiple passes until every node is balanced.

Steps:
For each node, check if it has more (or fewer) than one coin.
If a node has excess coins, try to move them to adjacent nodes.
Count every move.
Repeat the process until all nodes have exactly one coin.
Interviewer:
What would the time and space complexity of this brute-force approach be?

Interviewee:
The brute-force approach would likely have a high time complexity because in the worst-case we might need to iteratively inspect each node and perform moves until all nodes are balanced. Specifically, the time complexity could be O(n^2) due to possibly rechecking nodes multiple times.

The space complexity would be O(n) since we need storage proportional to the number of nodes to keep track of the coins distribution.

Optimized Approach
Interviewee:
To optimize, we can use Depth-First Search (DFS) to compute a more efficient solution. The idea is to treat moving coins in the tree as a flow problem where coins are transferred within the constraints until the nodes are balanced.

Optimized Steps:
Perform a DFS traversal of the tree.
For each node, calculate the excess or deficit coins after considering its sub-tree.
The total moves required will be the sum of absolute values of excess/deficit transferred to/from child nodes.
Interviewer:
Can you explain this with a drawing?

Drawing Explanation
Let's use the example root = [3, 0, 0]:

    3
   / \
  0   0
Starting at the root (node 3), it has 2 excess coins to be distributed: 3 - 1 = 2.
From node 3, it could move these 2 coins to its children (1 coin to each child).
    1
   / \
  1   1
Here, we'll need exactly 2 moves:

1 move to transfer a coin to the left child.
1 move to transfer a coin to the right child.
Interviewer:
What are the time and space complexities for this optimized approach?

Interviewee:
The time complexity is O(n) since we perform a single DFS traversal through the tree, visiting each node exactly once.

The space complexity is O(h) where h is the height of the tree, due to the recursive call stack. In the worst case for a skewed tree, this can be O(n). For a balanced tree, it would be O(log n).

Interviewer:
Okay, that sounds good. So, we can summarize that using a DFS traversal method is both time and space efficient for this problem.

Shall we move on to writing the actual code?

Interviewee:

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int moves = 0;
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left_excess = dfs(node.left);
        int right_excess = dfs(node.right);
        moves += Math.abs(left_excess) + Math.abs(right_excess);
        return node.val + left_excess + right_excess - 1;
    }

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }
}

// Time Complexity: O(n) where n is the number of nodes in the tree.
// Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
