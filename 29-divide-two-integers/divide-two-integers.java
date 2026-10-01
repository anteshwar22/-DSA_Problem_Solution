class Solution {
    public int divide(int dividend, int divisor) {

        // Overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to long to handle Integer.MIN_VALUE
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long result = 0;

        while (a >= b) {

            long temp = b;
            long multiple = 1;

            // Find largest power of 2 multiple
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            result += multiple;
        }

        if (negative) {
            result = -result;
        }

        return (int) result;
    }
}