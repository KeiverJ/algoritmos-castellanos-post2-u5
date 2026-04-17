package com.grafos;

import java.util.*;

public class BFS {

    public static int[] shortestDistances(WeightedDiGraph g, int src) {
        int V = g.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (WeightedDiGraph.Edge e : g.neighbors(u)) {
                if (dist[e.to()] == Integer.MAX_VALUE) {
                    dist[e.to()] = dist[u] + 1;
                    q.offer(e.to());
                }
            }
        }
        return dist;
    }
}
