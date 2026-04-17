package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class MSTTest {

    @Test
    void testKruskalBasic() {
        int[][] edges = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };

        int result = MST.kruskal(edges, 4);

        assertEquals(14, result);
    }

    @Test
    void testKruskalAnotherGraph() {
        int[][] edges = {
            {0, 1, 1},
            {0, 2, 4},
            {1, 2, 2},
            {1, 3, 6},
            {2, 3, 3}
        };

        int result = MST.kruskal(edges, 4);

        assertEquals(6, result);
    }

    @Test
    void testPrimBasic() {
        WeightedGraph g = new WeightedGraph(4);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);

        List<List<int[]>> adj = g.getAdjacencyList();
        int result = MST.prim(adj, 4);

        assertEquals(14, result);
    }

    @Test
    void testPrimAnotherGraph() {
        WeightedGraph g = new WeightedGraph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 6);
        g.addEdge(2, 3, 3);

        List<List<int[]>> adj = g.getAdjacencyList();
        int result = MST.prim(adj, 4);

        assertEquals(6, result);
    }

    @Test
    void testKruskalAndPrimSameResult() {
        int[][] edges = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };

        int kruskalResult = MST.kruskal(edges, 4);

        WeightedGraph g = new WeightedGraph(4);
        for (int[] e : edges) {
            g.addEdge(e[0], e[1], e[2]);
        }
        int primResult = MST.prim(g.getAdjacencyList(), 4);

        assertEquals(kruskalResult, primResult);
    }
}
