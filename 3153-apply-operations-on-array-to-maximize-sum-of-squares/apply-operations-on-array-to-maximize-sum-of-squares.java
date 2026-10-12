import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = 1_000_000_007;
        // Frequency array to store count of set bits at each of the 30 bit positions
        int[] bitCounts = new int[30];
        
        // Step 1: Count the total number of '1' bits at each position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Step 2: Greedily construct the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 30; bit++) {
                // If a '1' bit is available at this position, pull it into the current number
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--; // Consume the bit
                }
            }
            // Square the constructed number and add it to our modulo total
            long squared = (currentNum * currentNum) % mod;
            totalSumOfSquares = (totalSumOfSquares + squared) % mod;
        }
        
        return (int) totalSumOfSquares;
    }
}
