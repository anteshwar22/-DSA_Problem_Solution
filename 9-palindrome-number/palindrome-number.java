class Solution {
    public boolean isPalindrome(int x) {

       int reverse=0;
       int temp=Math.abs(x);
       if(x<0) return false;
        while(temp!=0){
        reverse=(reverse*10)+(temp%10);
        temp=temp/10;
        } 
        return (reverse==Math.abs(x));
    }
}