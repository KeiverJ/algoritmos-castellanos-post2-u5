package com.grafos;

import java.util.*;

public class TopSort {

    public static List<Integer> kahn(List<List<Integer>> adj, int V) {
        int[] in = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                in[v]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int u = 0; u < V; u++) {
            if (in[u] == 0) q.offer(u);
        }
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj.get(u)) {
                if (--in[v] == 0) q.offer(v);
            }
        }
        if (order.size() < V) {
            throw new IllegalArgumentException("Ciclo detectado en el grafo");
        }
        return order;
    }
}
