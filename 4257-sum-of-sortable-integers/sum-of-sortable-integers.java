import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        
        // 1. Precalculate prefix maximums
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        
        // 2. Precalculate suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        // 3. Track strict drop points where order decreases
        int[] prefixDrops = new int[n];
        for (int i = 1; i < n; i++) {
            prefixDrops[i] = prefixDrops[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        }
        
        int totalSum = 0;
        
        // 4. Validate each factor/divisor k of the array length n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue; 
            }
            
            boolean isValidDivisor = true;
            
            for (int i = 0; i < n; i += k) {
                int start = i;
                int end = i + k - 1;
                
                // Determine drop count entirely within the subarray window
                int internalDrops = prefixDrops[end] - prefixDrops[start];
                
                // Add the circular boundary drop condition (end of subarray back to start)
                if (nums[end] > nums[start]) {
                    internalDrops++;
                }
                
                // A subarray can be sorted cyclically if and only if it has at most 1 circular drop
                if (internalDrops > 1) {
                    isValidDivisor = false;
                    break;
                }
                
                // Ensure global bounds: non-decreasing monotonicity across adjacent blocks
                if (start > 0) {
                    if (prefixMax[start - 1] > suffixMin[start]) {
                        isValidDivisor = false;
                        break;
                    }
                }
            }
            
            if (isValidDivisor) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }
}
