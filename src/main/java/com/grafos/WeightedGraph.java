package com.grafos;

import java.util.*;

public class WeightedGraph {
    public record Edge(int to, int weight) {}

    private final int V;
    private final List<List<Edge>> adj;

    public WeightedGraph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w));
    }

    public int size() {
        return V;
    }

    public List<Edge> neighbors(int u) {
        return adj.get(u);
    }

    public List<List<int[]>> getAdjacencyList() {
        List<List<int[]>> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            List<int[]> row = new ArrayList<>();
            for (Edge e : adj.get(i)) {
                row.add(new int[]{e.to(), e.weight()});
            }
            result.add(row);
        }
        return result;
    }
}
