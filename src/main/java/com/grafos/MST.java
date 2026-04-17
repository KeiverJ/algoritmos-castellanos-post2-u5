package com.grafos;

import java.util.*;

public class MST {

    public static int kruskal(int[][] edges, int V) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        UnionFind uf = new UnionFind(V);
        int total = 0, count = 0;
        for (int[] e : edges) {
            if (uf.find(e[0]) != uf.find(e[1])) {
                uf.union(e[0], e[1]);
                total += e[2];
                if (++count == V - 1) break;
            }
        }
        return total;
    }

    public static int prim(List<List<int[]>> adj, int V) {
        boolean[] in = new boolean[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});

        int total = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[1];
            if (in[u]) continue;
            in[u] = true;
            total += cur[0];
            for (int[] e : adj.get(u)) {
                if (!in[e[0]] && e[1] < key[e[0]]) {
                    key[e[0]] = e[1];
                    pq.offer(new int[]{e[1], e[0]});
                }
            }
        }
        return total;
    }
}
