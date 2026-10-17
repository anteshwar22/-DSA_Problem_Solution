class Solution {
    public int climbStairs(int n) {
        if(n<=2)
        {
            return n;
        }
        int one=2;
        int two=1;
        int current=0;
        for(int i=3;i<n+1;i++)
        {
            current=one+two;

            two=one;
            one=current;
        }
        return current;
    }
}