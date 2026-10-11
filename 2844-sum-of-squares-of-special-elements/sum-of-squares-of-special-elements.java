class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        
        // Loop through 1-based index positions
        for (int i = 1; i <= n; i++) {
            // Check if 'i' divides 'n' evenly
            if (n % i == 0) {
                // Square the number at index i - 1 and add to totalSum
                totalSum += nums[i - 1] * nums[i - 1];
            }
        }
        
        return totalSum;
    }
}
