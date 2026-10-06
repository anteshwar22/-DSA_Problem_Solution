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
    public List<List<Integer>> levelOrder(TreeNode root) {

       List<List<Integer>> res = new ArrayList<>();
       Queue<TreeNode> q = new LinkedList<>();

       if (root == null) {
    return res;
}
       q.add(root);
        while (!q.isEmpty())
        {
                int lvl_size=q.size();
                List<Integer> tmp = new ArrayList<>(lvl_size);
                while(lvl_size>0)
                {
                    TreeNode t=q.remove();
                    tmp.add(t.val);
                    if(t.left !=null)
                    {
                        q.add(t.left);
                    }
                    if(t.right !=null)
                    {
                        q.add(t.right);
                    }
                    lvl_size--;

                }
                res.add(tmp);
        }
        return res;
    }
}