class Solution {
    public int scoreOfParentheses(String s) {
        Stack <Integer> stack =new Stack<>();
        
        for(char ch : s.toCharArray())
        {
            if(ch=='(')
            {
                stack.push(0);
            }
            else
            {
                int inner=stack.pop();
                int score=(inner==0)?1:2*inner;
                int outer;
                if(!stack.empty())
                {
                   
                   outer=stack.pop();
                   
                }
                else
                { outer=0;
                }
                stack.push(score + outer);

            }
        }
        return stack.peek();
    }
}