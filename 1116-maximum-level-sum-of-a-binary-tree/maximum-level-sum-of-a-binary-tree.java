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
    public int maxLevelSum(TreeNode root) {
     
        Queue <TreeNode> q=new LinkedList<>();
        if(root==null)
        {
            return 0;
        }
        q.add(root);
     
        int maxsum=Integer.MIN_VALUE;
        int maxlevel=1;
        int currlevel=1;
        while(!q.isEmpty())
        {
            int lvl=q.size();
            int lvlsum=0;

            while(lvl>0)
            {
                TreeNode t=q.remove();
                lvlsum+=t.val;
                if(t.left!=null)
                {
                    q.add(t.left);
                }
                if(t.right!=null)
                {
                    q.add(t.right);
                }
                lvl--;
            }
            if(lvlsum>maxsum)
            {
                maxsum=lvlsum;
                maxlevel=currlevel;
            }
           currlevel++;
        }
       return maxlevel;
    }
}