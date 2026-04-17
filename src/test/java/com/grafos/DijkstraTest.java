package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void testDijkstraBasic() {
        WeightedDiGraph g = new WeightedDiGraph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 9);
        g.addEdge(2, 4, 3);
        g.addEdge(3, 4, 4);

        int[] dist = Dijkstra.run(g, 0);

        assertEquals(0, dist[0]);
        assertEquals(7, dist[1]);
        assertEquals(5, dist[2]);
        assertEquals(8, dist[3]);
        assertEquals(8, dist[4]);
    }

    @Test
    void testDijkstraUnreachable() {
        WeightedDiGraph g = new WeightedDiGraph(4);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);

        int[] dist = Dijkstra.run(g, 0);

        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
        assertEquals(3, dist[2]);
        assertEquals(Integer.MAX_VALUE, dist[3]);
    }

    @Test
    void testDijkstraPath() {
        WeightedDiGraph g = new WeightedDiGraph(5);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 1);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 8);
        g.addEdge(2, 4, 10);
        g.addEdge(3, 4, 2);

        var path = Dijkstra.path(g, 0, 3);

        assertEquals(3, path.size());
        assertEquals(0, (int) path.get(0));
        assertEquals(1, (int) path.get(1));
        assertEquals(3, (int) path.get(2));
    }

    @Test
    void testDijkstraPathUnreachable() {
        WeightedDiGraph g = new WeightedDiGraph(3);
        g.addEdge(0, 1, 2);

        var path = Dijkstra.path(g, 0, 2);

        assertTrue(path.isEmpty());
    }
}
