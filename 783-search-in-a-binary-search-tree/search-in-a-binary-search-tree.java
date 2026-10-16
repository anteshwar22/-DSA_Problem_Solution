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
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode result;
        if(root==null) return null;
        if(root.val==val) return root;
        if(root.val<val)
        {
          result=findright(root.right,val);
        }
        else
        {
             result=findleft(root.left,val);
        }
        return result;

    }
    public TreeNode findright(TreeNode right,int val)
    {   TreeNode result;
        if(right==null) return null;
        if(right.val==val) return right;
        if(right.val<val)
        {
           result=findright(right.right,val);
        }
        else
        {
            result=findleft(right.left,val);
        }
        return result;
    }
    public TreeNode findleft(TreeNode left,int val)
    { 
         TreeNode result;
        if(left==null) return null;
        if(left.val==val) return left;
        if(left.val<val)
        {
          result= findright(left.right,val);
        }
        else
        {
           result= findleft(left.left,val);

        }
        return result;
    }
}