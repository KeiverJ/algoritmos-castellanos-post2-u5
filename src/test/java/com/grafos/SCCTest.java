package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class SCCTest {

    @Test
    void testKosarajuTwoCycles() {
        WeightedDiGraph g = new WeightedDiGraph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(1, 2, 1);

        List<List<Integer>> sccs = SCC.kosaraju(g);

        assertEquals(3, sccs.size());

        Set<Integer> sizes = new HashSet<>();
        for (List<Integer> scc : sccs) {
            sizes.add(scc.size());
        }
        assertTrue(sizes.contains(1));
        assertTrue(sizes.contains(2));
    }

    @Test
    void testKosarajuSingleSCC() {
        WeightedDiGraph g = new WeightedDiGraph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);

        List<List<Integer>> sccs = SCC.kosaraju(g);

        assertEquals(1, sccs.size());
        assertEquals(3, sccs.get(0).size());
    }

    @Test
    void testKosarajuAllIsolated() {
        WeightedDiGraph g = new WeightedDiGraph(3);

        List<List<Integer>> sccs = SCC.kosaraju(g);

        assertEquals(3, sccs.size());
    }

    @Test
    void testKosarajuLinear() {
        WeightedDiGraph g = new WeightedDiGraph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);

        List<List<Integer>> sccs = SCC.kosaraju(g);

        assertEquals(4, sccs.size());
    }
}
