package com.grafos;

import java.util.*;

public class BellmanFord {
    public record Result(int[] dist, boolean hasNegCycle) {}

    public static Result run(int[][] edges, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 1; i < V; i++) {
            boolean updated = false;
            for (int[] e : edges) {
                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) {
                    dist[e[1]] = dist[e[0]] + e[2];
                    updated = true;
                }
            }
            if (!updated) break;
        }
        boolean neg = false;
        for (int[] e : edges) {
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) {
                neg = true;
            }
        }
        return new Result(dist, neg);
    }
}
