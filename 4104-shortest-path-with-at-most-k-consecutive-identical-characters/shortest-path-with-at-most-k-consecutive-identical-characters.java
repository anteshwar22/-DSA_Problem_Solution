import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build the adjacency list: u -> List of {v, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adj.get(u).add(new int[]{v, weight});
            // If the graph is undirected, uncomment the line below:
            // adj.get(v).add(new int[]{u, weight});
        }

        // dist[u][streak] tracks the minimum cost to reach node u with a given streak length
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Priority queue for Dijkstra: element format is {cost, node, streak}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize from node 0 (the starting node has a streak of 1)
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        // FIXED: Changed !pq.empty() to !pq.isEmpty()
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int streak = curr[2];

            // If we reached the destination node, return the distance
            if (u == n - 1) {
                return d;
            }

            // Skip processing if we found a better way to reach this state already
            if (d > dist[u][streak]) {
                continue;
            }

            // Traverse neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Check character continuation to increment or reset streak
                int nextStreak = (labels.charAt(u) == labels.charAt(v)) ? (streak + 1) : 1;

                // Only proceed if the consecutive matching character streak is valid
                if (nextStreak <= k) {
                    if (d + weight < dist[v][nextStreak]) {
                        dist[v][nextStreak] = d + weight;
                        pq.offer(new int[]{d + weight, v, nextStreak});
                    }
                }
            }
        }

        // Return -1 if destination node n-1 is unreachable under the rules
        return -1;
    }
}
