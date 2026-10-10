class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // The maximum possible difference given the problem constraints (e.g., nums[i] <= 10^5)
        int maxDiff = 0;
        int[] diffFreq = new int[100001];
        
        // Calculate initial differences and count their frequencies
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > maxDiff) {
                maxDiff = diff;
            }
            diffFreq[diff]++;
        }
        
        // Greedily reduce the largest differences using bucket/frequency manipulation
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (diffFreq[d] == 0) {
                continue;
            }
            
            // Determine how many elements at the current difference 'd' we can reduce
            long count = diffFreq[d];
            long take = Math.min(count, k);
            
            diffFreq[d] -= take;
            diffFreq[d - 1] += take;
            k -= take;
        }
        
        // Calculate the final sum of squared differences
        long minSumSquare = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (diffFreq[d] > 0) {
                minSumSquare += (long) diffFreq[d] * d * d;
            }
        }
        
        return minSumSquare;
    }
}

