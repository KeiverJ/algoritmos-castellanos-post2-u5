package com.grafos;

import java.util.*;

public class Benchmark {
    public static void main(String[] args) {
        int[] sizes = {1000, 5000};
        int warmup = 3;
        int iterations = 5;

        System.out.println("===========================================");
        System.out.println("Benchmark: Dijkstra vs BFS en Grafos No Ponderados");
        System.out.println("===========================================\n");

        for (int V : sizes) {
            System.out.println("Vertices: " + V);
            System.out.println("-".repeat(40));

            WeightedDiGraph weightedGraph = new WeightedDiGraph(V);
            WeightedDiGraph unweightedGraph = new WeightedDiGraph(V);
            Random random = new Random(42);
            int edgesPerNode = 3;

            for (int u = 0; u < V; u++) {
                for (int j = 0; j < edgesPerNode; j++) {
                    int v = random.nextInt(V);
                    int w = random.nextInt(10) + 1;
                    weightedGraph.addEdge(u, v, w);
                    unweightedGraph.addEdge(u, v, 1);
                }
            }

            long dijkstraTime = 0;
            long bfsTime = 0;

            for (int i = 0; i < warmup + iterations; i++) {
                long start = System.nanoTime();
                Dijkstra.run(weightedGraph, 0);
                long end = System.nanoTime();
                dijkstraTime += (end - start);

                start = System.nanoTime();
                BFS.shortestDistances(unweightedGraph, 0);
                end = System.nanoTime();
                bfsTime += (end - start);
            }

            double dijkstraOps = (iterations * 1e9) / (dijkstraTime / (warmup + iterations));
            double bfsOps = (iterations * 1e9) / (bfsTime / (warmup + iterations));
            double ratio = dijkstraOps > 0 ? bfsOps / dijkstraOps : 0;

            System.out.printf("Dijkstra: %,9.0f ops/s%n", dijkstraOps);
            System.out.printf("BFS:      %,9.0f ops/s%n", bfsOps);
            System.out.printf("Ratio BFS/Dijkstra: %.2fx%n", ratio);
            System.out.println();
        }

        System.out.println("===========================================");
        System.out.println("Analisis: BFS es mas rapido que Dijkstra en");
        System.out.println("grafos no ponderados porque no necesita");
        System.out.println("gestionar una cola de prioridad (heap).");
        System.out.println("===========================================");
    }
}
