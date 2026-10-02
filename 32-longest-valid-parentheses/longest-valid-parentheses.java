class Solution {
    public int longestValidParentheses(String s) {
        int left=0;
        int right=0;
        int maxlen=0;
        for(int i=0;i<s.length();i++)
        {
           char ch=s.charAt(i);
           if(ch=='(') left++;
           else right++;

           if(left==right)
           {
             maxlen=Math.max(maxlen,2*right);
           }
           else if (right > left) {
                left = right = 0; // Sequence toota! Safe reset
            }

        }

        left=right=0;
          for(int j=s.length()-1;j>=0;j--)
        {
           char ch=s.charAt(j);
           if(ch=='(') left++;
           else right++;

           if(left==right)
           {
             maxlen=Math.max(maxlen,2*right);
           }
           else if (left > right) {
                left = right = 0; // Sequence toota! Safe reset
            }

        }

        return maxlen;

    }
}