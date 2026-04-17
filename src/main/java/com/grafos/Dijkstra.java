package com.grafos;

import java.util.*;

public class Dijkstra {

    public static int[] run(WeightedDiGraph g, int src) {
        int V = g.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (d > dist[u]) continue;
            for (WeightedDiGraph.Edge e : g.neighbors(u)) {
                int nd = dist[u] + e.weight();
                if (nd < dist[e.to()]) {
                    dist[e.to()] = nd;
                    pq.offer(new int[]{nd, e.to()});
                }
            }
        }
        return dist;
    }

    public static List<Integer> path(WeightedDiGraph g, int src, int dst) {
        int V = g.size();
        int[] dist = new int[V], parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (u == dst) break;
            if (d > dist[u]) continue;
            for (WeightedDiGraph.Edge e : g.neighbors(u)) {
                int nd = dist[u] + e.weight();
                if (nd < dist[e.to()]) {
                    dist[e.to()] = nd;
                    parent[e.to()] = u;
                    pq.offer(new int[]{nd, e.to()});
                }
            }
        }
        if (dist[dst] == Integer.MAX_VALUE) return List.of();
        List<Integer> p = new ArrayList<>();
        for (int v = dst; v != -1; v = parent[v]) p.add(v);
        Collections.reverse(p);
        return p;
    }
}
