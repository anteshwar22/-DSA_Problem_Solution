import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum, sumSq, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;

            update(1, 0, n - 1, left, i);

            last[nums[i]] = i;
            ans = (ans + sumSq[1]) % MOD;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long k) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sumSq[node] = (
            sumSq[node] + 2 * k % MOD * oldSum % MOD
            + k * k % MOD * len % MOD
        ) % MOD;

        sum[node] = (oldSum + k * len) % MOD;
        lazy[node] = (lazy[node] + k) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        long k = lazy[node];

        apply(node * 2, l, mid, k);
        apply(node * 2 + 1, mid + 1, r, k);

        lazy[node] = 0;
    }

    private void update(
        int node, int l, int r, int ql, int qr
    ) {
        if (qr < l || r < ql) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }
}