class Solution {
    public int minInsertions(String s) {
        
        int depth=0;
        int moves=0;
        for(char ch:s.toCharArray())
        {
            if(ch=='(')
            {
                if(depth%2==1)
                {
                    depth-=1;
                    moves+=1;
                }
                depth+=2;
            }
            else
            {
                depth-=1;
                if(depth<0)
                {
                    depth+=2;
                    moves+=1;
                }
            }
        }
        return moves+depth;
    }
}