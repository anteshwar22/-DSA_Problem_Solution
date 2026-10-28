class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int K = 1024;

        boolean[][] prev = new boolean[n][K];
        boolean[][] curr = new boolean[n][K];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < K; x++) {
                    curr[j][x] = false;
                }

                if (i == 0 && j == 0) {
                    curr[j][grid[i][j]] = true;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < K; x++) {
                        if (prev[j][x]) {
                            curr[j][x ^ grid[i][j]] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < K; x++) {
                        if (curr[j - 1][x]) {
                            curr[j][x ^ grid[i][j]] = true;
                        }
                    }
                }
            }

            boolean[][] temp = prev;
            prev = curr;
            curr = temp;
        }

        for (int x = 0; x < K; x++) {
            if (prev[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}