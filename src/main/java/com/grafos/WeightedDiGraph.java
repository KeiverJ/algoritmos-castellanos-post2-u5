package com.grafos;

import java.util.*;

public class WeightedDiGraph {
    public record Edge(int to, int weight) {}

    private final int V;
    private final List<List<Edge>> adj;

    public WeightedDiGraph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
    }

    public int size() {
        return V;
    }

    public List<Edge> neighbors(int u) {
        return adj.get(u);
    }

    public WeightedDiGraph transpose() {
        WeightedDiGraph t = new WeightedDiGraph(V);
        for (int u = 0; u < V; u++) {
            for (Edge e : adj.get(u)) {
                t.addEdge(e.to(), u, e.weight());
            }
        }
        return t;
    }
}
