class Solution {
    public int removeDuplicates(int[] nums) {
     int count=0;
     int k=1;
    
     int uni[]=new int[20];
     for(int i=1;i<nums.length;i++)
     {
        if(nums[i]!=nums[i-1])
        {
            nums[k]=nums[i];
            k++;
        }

     }
     return k;
    }
}