import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(s);
        visited.add(s);

        boolean foundValidLevel = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process the current level entirely
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();

                if (isValid(current)) {
                    result.add(current);
                    foundValidLevel = true;
                }

                // If a valid string is found at this level, do not generate the next level
                if (foundValidLevel) continue;

                // Generate all possible states by removing one parenthesis at a time
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    if (c != '(' && c != ')') continue; // Skip alphabets

                    // Form a new string omitting the character at index j
                    String nextState = current.substring(0, j) + current.substring(j + 1);

                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.add(nextState);
                    }
                }
            }

            // Stop going to deeper levels if valid strings were found at this depth
            if (foundValidLevel) break;
        }

        return result;
    }

    // Helper method to check if a string has valid parentheses balance
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing brackets than opening
            }
        }
        return count == 0;
    }
}
