package com.grafos;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

@State(Scope.Thread)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class GraphBenchmark {

    @Param({"1000", "5000"})
    public int V;

    public WeightedDiGraph weightedGraph;
    public WeightedDiGraph unweightedGraph;
    public Random random;

    @Setup
    public void setup() {
        random = new Random(42);
        weightedGraph = new WeightedDiGraph(V);
        unweightedGraph = new WeightedDiGraph(V);

        int edgesPerNode = 3;
        for (int u = 0; u < V; u++) {
            for (int j = 0; j < edgesPerNode; j++) {
                int v = random.nextInt(V);
                int w = random.nextInt(10) + 1;
                weightedGraph.addEdge(u, v, w);
                unweightedGraph.addEdge(u, v, 1);
            }
        }
    }

    @Benchmark
    public int[] dijkstraBenchmark() {
        return Dijkstra.run(weightedGraph, 0);
    }

    @Benchmark
    public int[] bfsBenchmark() {
        return BFS.shortestDistances(unweightedGraph, 0);
    }
}
