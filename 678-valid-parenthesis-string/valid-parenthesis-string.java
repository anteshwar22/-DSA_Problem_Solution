class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible open brackets remaining
        int maxOpen = 0; // Maximum possible open brackets remaining

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                minOpen--;  // Treat '*' as ')'
                maxOpen++;  // Treat '*' as '('
            }

            // If maxOpen is negative, there are too many closing brackets ')' 
            // and no amount of '*' can compensate for it.
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot fall below 0 because we cannot have a negative 
            // balance of open brackets at any point.
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // The string is valid if we can successfully close all open brackets.
        return minOpen == 0;
    }
}
