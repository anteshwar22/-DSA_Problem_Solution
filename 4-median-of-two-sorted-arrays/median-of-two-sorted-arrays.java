class Solution {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1=nums1.length;
        int l2=nums2.length;

        int mergeArray[]=new int[l1+l2];

        twoArrayMerge(nums1,nums2,mergeArray,l1,l2);
         int n = mergeArray.length;
            int mid = n / 2;

            if (n % 2 == 1) {
                return mergeArray[mid];                                // odd
            } else {
                return (mergeArray[mid - 1] + mergeArray[mid]) / 2.0;  // even
            }
    }
    public void  twoArrayMerge(int[] nums1,int[] nums2,int[] mergeArray,int l1,int l2)
    {  
      int count=0;
      int i=0;
      int j=0;
      while(i<l1&&j<l2)
      {
          if(nums1[i]<nums2[j])
          {
             mergeArray[count]=nums1[i];
             count++;
             i++;
          }
          else if(nums1[i]>nums2[j])
          {
             mergeArray[count]=nums2[j];
             count++;
             j++;
          }
            else if(nums1[i]==nums2[j])
          {
             mergeArray[count]=nums2[j];
             count++;
             j++;
            
          }
      }
      while(i<l1)
      {
        mergeArray[count]=nums1[i];
        count++;
        i++;
      }
      while(j<l2)
      {
         mergeArray[count]=nums2[j];
        count++;
        j++;
      }
    }
}