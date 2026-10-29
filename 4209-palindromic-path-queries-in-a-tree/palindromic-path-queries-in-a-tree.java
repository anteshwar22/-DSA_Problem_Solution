import java.util.*;

class Solution {
    int LOG;
    List<Integer>[] adj;
    int[][] up;
    int[] depth, tin, tout, parent, prefix;
    int timer;

    int[] bit;

    void update(int i, int val) {
        while (i < bit.length) {
            bit[i] ^= val;
            i += i & -i;
        }
    }

    int query(int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }

    void rangeUpdate(int l, int r, int val) {
        update(l, val);
        update(r + 1, val);
    }

    int getPrefix(int u) {
        return prefix[u] ^ query(tin[u]);
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        prefix = new int[n];
        up = new int[LOG][n];
        bit = new int[n + 2];

        char[] chars = s.toCharArray();
        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int[] order = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = ++timer;
            order[count++] = u;

            prefix[u] =
                    (u == 0 ? 0 : prefix[parent[u]])
                    ^ (1 << (chars[u] - 'a'));

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            tout[u] = Math.max(tout[u], tin[u]);

            if (u != 0) {
                int p = parent[u];
                tout[p] = Math.max(tout[p], tout[u]);
            }
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                int delta =
                        (1 << (chars[u] - 'a'))
                        ^ (1 << (c - 'a'));

                if (delta != 0) {
                    rangeUpdate(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int mask = getPrefix(u)
                        ^ getPrefix(v)
                        ^ (1 << (chars[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }
}