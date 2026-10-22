import java.util.function.IntPredicate;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index where 0 is located
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int finalZero = zero;
        // Lambda to verify if the cyclic sequence follows a valid sorted step direction
        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };

        int ans = Integer.MAX_VALUE;

        // Case 1: Array is a cyclic increasing sequence
        if (check.test(1)) {
            ans = Math.min(ans, zero);         // Move 0 to front via direct left rotations
            ans = Math.min(ans, n - zero + 2); // Reverse -> Left Rotations -> Reverse back
        }

        // Case 2: Array is a cyclic decreasing sequence
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);     // Left rotations -> Reverse
            ans = Math.min(ans, n - zero);     // Reverse first -> Left rotations
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
