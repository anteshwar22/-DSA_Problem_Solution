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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
           List<List<Integer>> res=new ArrayList<>();
           Queue<TreeNode> q=new LinkedList<>();

           if(root ==null)
           {
             return res;
           }
           q.add(root);
            boolean leftright=true;
           while(!q.isEmpty())
           {
               int qsize=q.size();
               List <Integer> tmp=new ArrayList<>(qsize);
              
               while(qsize>0)
               {   

                   
                        TreeNode t=q.remove();
                        if(leftright){
                        tmp.add(t.val);
                        }
                        else
                        {
                            tmp.addFirst(t.val);
                        }
                        if(t.left!=null)
                        {
                            q.add(t.left);

                        }
                        if(t.right!=null)
                        {
                            q.add(t.right);
                        }
                        qsize--;
               }

             res.add(tmp);
             leftright= !leftright;
           }
           return res;
    }
}