package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void testBFSSimple() {
        WeightedDiGraph g = new WeightedDiGraph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);

        int[] dist = BFS.shortestDistances(g, 0);

        assertEquals(0, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(1, dist[2]);
        assertEquals(2, dist[3]);
        assertEquals(3, dist[4]);
    }

    @Test
    void testBFSUnreachable() {
        WeightedDiGraph g = new WeightedDiGraph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);

        int[] dist = BFS.shortestDistances(g, 0);

        assertEquals(0, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(1, dist[2]);
        assertEquals(Integer.MAX_VALUE, dist[3]);
    }

    @Test
    void testBFSStartNode() {
        WeightedDiGraph g = new WeightedDiGraph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);

        int[] dist = BFS.shortestDistances(g, 1);

        assertEquals(Integer.MAX_VALUE, dist[0]);
        assertEquals(0, dist[1]);
        assertEquals(1, dist[2]);
    }
}
