class Solution {
    public boolean isValid(String s) {
        Stack<Character>  stack =new Stack<>();
    if(s.length()<=1) return false;

     for(int i=0;i<s.length();i++)
     {
        char ch=s.charAt(i);

        switch(ch){
            case '(':
                     stack.push(')');
                     break;
            case '[':
                    stack.push(']');
                    break;
            case '{':
                    stack.push('}');
                    break;
            default:
                    if(stack.empty() || stack.pop() !=ch)
                    {
                        return false;
                    }
        }
   
     }

          return stack.empty();
    }
}