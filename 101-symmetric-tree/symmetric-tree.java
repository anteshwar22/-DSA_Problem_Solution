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
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return false;
        TreeNode l=root.left;
        TreeNode r=root.right;
        
        return issame(l,r);
    }

    public boolean issame(TreeNode l,TreeNode r)
    {

        if(l==null && r==null) return true;
        if(l==null || r==null) return false;
        if(l.val != r.val) return false;
       boolean p1= issame(l.left,r.right); 
       boolean p2=issame(l.right,r.left);
       return issame(l.left, r.right) && issame(l.right, r.left);
    }
}