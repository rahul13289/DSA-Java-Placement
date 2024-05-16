/*

Description: 

You are given the root of a full binary tree with the following properties:

Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
The evaluation of a node is as follows:

If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.
Return the boolean result of evaluating the root node.

A full binary tree is a binary tree where each node has either 0 or 2 children.

A leaf node is a node that has zero children.

Solution:

Intuition

To recursively evaluate the boolean expression represented by the binary tree, propagating the results from leaf nodes upwards and applying the corresponding boolean operations at non-leaf nodes.

Approach: DFS

In a DFS traversal, we explore as far as possible along each branch before backtracking, the recursive helper function performs a DFS traversal by recursively evaluating the left and right subtrees (children) of each non-leaf node.

Here's how the DFS traversal works:

Base Cases:

If a node has no children (i.e., it's a leaf), we return its boolean value directly.
If a node has two children, we determine whether it represents an OR operation (if its value is 2) or an AND operation (if its value is 3).
Recursion:

For non-leaf nodes, we evaluate its left and right children recursively.
Combine Results:

For OR nodes, we return true if either the left or right child evaluates to true.
For AND nodes, we return true only if both the left and right child evaluate to true.
Return:

And at last we return the final boolean result obtained from evaluating the root node.
So, we check whether a node is a leaf (no children), an OR node (value 2), or an AND node (value 3), and we then recursively evaluates its children accordingly.

Dry-Run:
Lets dry run for the given example:

        2
       / \
      1   3
         / \
        0   1
Step 1: Evaluate the leaf nodes.

Node with value 1 evaluates to true.
Node with value 0 evaluates to false.
Node with value 1 evaluates to true.
Step 2: Evaluate the non-leaf node with value 3 (AND operation).

Left child (value 0) evaluates to false.
Right child (value 1) evaluates to true.
Perform AND operation: false AND true = false.
Step 3: Evaluate the root node with value 2 (OR operation).

Left child (value 1) evaluates to true.
Right child (value 3) evaluates to false (from Step 2).
Perform OR operation: true OR false = true.
Step 4: Return the final result.

The root node evaluates to true, so the output is true.
Thus, the output for the given binary tree is true.

Complexity
Time complexity: O(n)

Space complexity: O(n)

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
    public boolean helper(TreeNode root) {
     
     if(root.val == 0 || root.val == 1){
         
         return root.val == 1;

     } else if(root.val == 2){
        
         return helper(root.left) || helper(root.right);

     } else if(root.val == 3){
         
         return helper(root.left) && helper(root.right);
     }
      
       return false;

    }    
    
    public boolean evaluateTree(TreeNode root){

        return helper(root);
    }

}
