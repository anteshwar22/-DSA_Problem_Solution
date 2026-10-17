import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Step 1: Create a 2D array to store [digitSum, originalValue] pairs
        int[][] pairedArr = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairedArr[i][0] = getDigitSum(nums[i]);
            pairedArr[i][1] = nums[i];
        }
        
        // Step 2: Sort based on digit sum, breaking ties with the actual numerical value
        Arrays.sort(pairedArr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        // Step 3: Map each element to its correct target index in the sorted array
        Map<Integer, Integer> targetPosMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetPosMap.put(pairedArr[i][1], i);
        }
        
        // Step 4: Traverse the array to count permutation cycles
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            // Skip if already in the correct position or already part of a processed cycle
            if (visited[i] || targetPosMap.get(nums[i]) == i) {
                continue;
            }
            
            // Trace the length of the cycle
            int cycleSize = 0;
            int currentIdx = i;
            
            while (!visited[currentIdx]) {
                visited[currentIdx] = true;
                // Move to the next index where the current value belongs
                currentIdx = targetPosMap.get(nums[currentIdx]);
                cycleSize++;
            }
            
            // A cycle of size k takes k - 1 swaps to fix
            if (cycleSize > 0) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    
    // Helper function to calculate the sum of digits
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
