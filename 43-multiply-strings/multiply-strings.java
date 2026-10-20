class Solution {
    public String multiply(String num1, String num2) {
        // Edge case: if either number is "0", the result is "0"
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        int n1 = num1.length();
        int n2 = num2.length();
        // The product of two numbers of length n1 and n2 will have at most n1 + n2 digits
        int[] result = new int[n1 + n2];
        
        // Loop from right to left
        for (int i = n1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0'; // Convert character to integer
            
            for (int j = n2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                
                // Multiply current digits and add to the previous carry
                int product = digit1 * digit2;
                int sum = product + result[i + j + 1];
                
                // Store the unit digit at the current position
                result[i + j + 1] = sum % 10;
                // Add the carry value to the next position to the left
                result[i + j] += sum / 10;
            }
        }
        
        // Build the final string, skipping any leading zeros
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) { // Skip leading zero
                sb.append(digit);
            }
        }
        
        return sb.toString();
    }
}
