class Solution {
    public int maximizeSum(int[] nums, int k) {
        // Step 1: Find the maximum element in the array
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        
        // Step 2: Use the arithmetic progression formula to find the sum
        // The picked sequence will be: max, max + 1, max + 2, ..., max + (k - 1)
        return k * max + (k * (k - 1)) / 2;
    }
}
